package strategies;

import businessLogic.Server;
import interfaces.Strategy;
import models.Task;

import java.util.Comparator;
import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public boolean addTask(List<Server> servers, Task task) {
        var server = servers.stream().min(Comparator.comparingInt(Server::getTaskCount)).orElse(null);

        if(server == null) {
            return false;
        }

        return server.addTask(task);
    }
}
