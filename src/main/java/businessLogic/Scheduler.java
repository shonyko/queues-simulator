package businessLogic;

import interfaces.Strategy;
import models.Task;
import models.enums.SelectionPolicy;
import utils.StrategyDictionary;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Scheduler {
    private final ExecutorService executor;
    private final List<Server> servers;
    private final int maxNoServers;
    private final int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer, SelectionPolicy policy) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        servers = new CopyOnWriteArrayList<>();

        executor = Executors.newFixedThreadPool(maxNoServers);
        IntStream   .range(0, maxNoServers)
                    .forEach(n -> {
                        var server = new Server(maxTasksPerServer);
                        servers.add(server);
                        executor.execute(server);
                    });

        changeStrategy(policy);
    }

    public void changeStrategy(SelectionPolicy policy) {
        strategy = StrategyDictionary.get(policy);
    }

    public boolean dispatchTask(Task t) {
        return strategy.addTask(servers, t);
    }

    public List<Server> getServers() {
        return servers;
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}
