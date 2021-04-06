package businessLogic;

import interfaces.Logger;
import interfaces.SimulationConfiguration;
import models.ServerStats;
import models.Task;
import utils.TaskGenerator;
import models.SimulationResult;
import views.SimulationStatusView;
import views.SimulationVisualisationView;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationManager implements Runnable {
    private final SimulationConfiguration config;

    private final Scheduler scheduler;
    private final SimulationStatusView view;
    private final SimulationVisualisationView secondaryView;
    private final Logger logger;

    private final List<Task> generatedTasks;

    public SimulationManager(SimulationConfiguration config, SimulationStatusView view, SimulationVisualisationView secondaryView, Logger logger) {
        this.config = config;
        this.view = view;
        this.secondaryView = secondaryView;
        this.logger = logger;

        scheduler = new Scheduler(config.getNumberOfServers(), config.getMaxTasksPerServer(), config.getSelectionPolicy());
        generatedTasks = TaskGenerator.generateTasks(config);
    }

    private void dispatchClients(int currTime) {
        var clients = generatedTasks.stream()
                .filter(c -> c.getArrivalTime() <= currTime)
                .collect(Collectors.toList());

        for(var client : clients) {
            if(scheduler.dispatchTask(client)) {
                generatedTasks.remove(client);
            }
        }
    }

    private String createLog(int currTime) {
        var servers = scheduler.getServers();
        var sb = new StringBuilder();

        sb  .append(String.format("Time %d\n", currTime))
            .append("Waiting clients: ");

        if(generatedTasks.isEmpty()) {
            sb.append("none");
        }

        for(var task : generatedTasks) {
            sb.append(task).append("; ");
        }

        int index = 0;
        for(var server : servers) {
            sb  .append("\n")
                .append(String.format("Queue %d: ", ++index))
                .append(server);
        }

        return sb.append("\n\n").toString();
    }

    private ServerStats getServersStats(List<Server> servers) {
        return servers.stream()
                .reduce(new ServerStats(), (res, server) -> res.add(server.getServerStats()), ServerStats::add);
    }

    private int updateSimulationResult(SimulationResult result, int currTime) {
        int clientsNb = scheduler.getServers()
                .stream().reduce(0, (subtotal, server) -> subtotal + server.getTaskCount(), Integer::sum);
        if(clientsNb > result.getMaxClientsNb()) {
            result.setMaxClientsNb(clientsNb);
            result.setPeakHour(currTime);
        }

        var stats = getServersStats(scheduler.getServers());
        result.setAvgWaitTime((double) stats.getWaitedTime() / stats.getWaitedTasks());
        result.setAvgProcessTime((double) stats.getProcessedTime() / stats.getProcessedTasks());

        return clientsNb;
    }

    private void updateUI(SimulationResult result) {
        updateStatusView(result);
        updateSecondaryView();
    }

    private void updateStatusView(SimulationResult result) {
        SwingUtilities.invokeLater(() -> view.setResults(result));
    }

    private void updateSecondaryView() {
        secondaryView.updateLeftClients(generatedTasks);
        var servers = scheduler.getServers();
        servers.stream().reduce(0, (index, server) -> {
            secondaryView.updateQueue(index, server.getTasks());
            return index + 1;
        }, Integer::sum);
    }

    private void log(String log) {
        System.out.println(log);
        logger.log(log);
        SwingUtilities.invokeLater(() -> view.addLog(log));
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> view.setParams(config));

        var result = new SimulationResult();
        int currTime = 0;
        while (!Thread.interrupted()) {
            dispatchClients(currTime);

            log(createLog(currTime));
            var clientsNb = updateSimulationResult(result, currTime);

            updateUI(result);

            if(++currTime >= config.getTimeLimit() ||
                (generatedTasks.isEmpty() && clientsNb == 0)) {
                scheduler.shutdown();
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
        SwingUtilities.invokeLater(() -> view.setStatus("Finished"));
        log(result.toString());
    }
}
