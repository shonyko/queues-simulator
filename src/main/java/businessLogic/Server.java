package businessLogic;

import models.ServerStats;
import models.Task;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;

    private final AtomicInteger addedNumber;

    private final AtomicInteger waitingPeriod;
    private final AtomicInteger waitedTime;
    private final AtomicInteger waitedTasks;
    private final AtomicInteger processedTime;
    private final AtomicInteger processedTasks;

    private Task currTask;

    public Server(int maxTasks) {
        tasks = new ArrayBlockingQueue<>(maxTasks);
        waitingPeriod = new AtomicInteger(0);
        waitedTime = new AtomicInteger(0);
        waitedTasks = new AtomicInteger(0);
        addedNumber = new AtomicInteger(0);

        processedTime = new AtomicInteger(0);
        processedTasks = new AtomicInteger(0);
    }

    public boolean addTask(Task newTask) {
        if(!tasks.offer(newTask)) {
            return false;
        }

        waitingPeriod.addAndGet(newTask.getProcessingTime());
        addedNumber.incrementAndGet();
        return true;
    }

    private void updateStats() {
        int newTasks = addedNumber.get();
        if(newTasks > 0) {
            waitedTasks.addAndGet(newTasks);
            addedNumber.addAndGet(-newTasks);
        }

        currTask.setProcessingTime(currTask.getProcessingTime() - 1);
        processedTime.incrementAndGet();

        waitingPeriod.decrementAndGet();
        waitedTime.addAndGet(getTaskCount());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                currTask = null;
                currTask = tasks.take();
            } catch (InterruptedException e) {
                return;
            }

            boolean added = false;
            while (currTask.getProcessingTime() > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }

                if(!added) {
                    processedTasks.incrementAndGet();
                    added = true;
                }

                updateStats();
            }
        }
    }

    public List<Task> getTasks() {
        var list = new CopyOnWriteArrayList<>(tasks);
        if(currTask != null) {
            list.add(0, currTask);
        }

        return list;
    }

    public ServerStats getServerStats() {
        return new ServerStats(waitedTime.get(), waitedTasks.get(), processedTime.get(), processedTasks.get());
    }

    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }

    public int getTaskCount() {
        int cnt = tasks.size();
        if(currTask != null) {
            cnt++;
        }

        return cnt;
    }

    @Override
    public String toString() {
        if(currTask == null) {
            return "closed";
        }

        var sb = new StringBuilder();
        sb.append(currTask);

        for(var task : tasks) {
            sb.append(", ").append(task);
        }

        return sb.toString();
    }
}
