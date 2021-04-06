package utils;

import interfaces.SimulationConfiguration;
import models.Task;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskGenerator {
    private TaskGenerator() {

    }

    public static List<Task> generateTasks(SimulationConfiguration config) {
        var list = new CopyOnWriteArrayList<Task>();

        var rnd = new Random();
        var arrivalTimeIterator     = rnd.ints(config.getMinArrivalTime(), config.getMaxArrivalTime() + 1).iterator();
        var processingTimeIterator  = rnd.ints(config.getMinProcessingTime(), config.getMaxProcessingTime() + 1).iterator();
        for(int i = 0; i < config.getNumberOfClients(); i++) {
            var arrivalTime     = arrivalTimeIterator.nextInt();
            var processingTime  = processingTimeIterator.nextInt();
            list.add(new Task(i + 1, arrivalTime, processingTime));
        }

        list.sort(Comparator.comparingInt(Task::getArrivalTime));

        return list;
    }
}
