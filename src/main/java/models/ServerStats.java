package models;

public class ServerStats {
    private int waitedTime;
    private int waitedTasks;
    private int processedTime;
    private int processedTasks;

    public ServerStats() {

    }

    public ServerStats(int waitedTime, int waitedTasks, int processedTime, int processedTasks) {
        this.waitedTime = waitedTime;
        this.waitedTasks = waitedTasks;
        this.processedTime = processedTime;
        this.processedTasks = processedTasks;
    }

    public ServerStats add(ServerStats other) {
        return new ServerStats( waitedTime + other.getWaitedTime(), waitedTasks + other.getWaitedTasks(),
                                processedTime + other.getProcessedTime(), processedTasks + other.getProcessedTasks());
    }

    public int getWaitedTime() {
        return waitedTime;
    }

    public void setWaitedTime(int waitedTime) {
        this.waitedTime = waitedTime;
    }

    public int getWaitedTasks() {
        return waitedTasks;
    }

    public void setWaitedTasks(int waitedTasks) {
        this.waitedTasks = waitedTasks;
    }

    public int getProcessedTime() {
        return processedTime;
    }

    public void setProcessedTime(int processedTime) {
        this.processedTime = processedTime;
    }

    public int getProcessedTasks() {
        return processedTasks;
    }

    public void setProcessedTasks(int processedTasks) {
        this.processedTasks = processedTasks;
    }
}
