package models;

import interfaces.SimulationConfiguration;
import models.enums.SelectionPolicy;

public class SimulationConfig implements SimulationConfiguration {
    private int numberOfClients;
    private int numberOfServers;
    private int timeLimit;
    private int maxTasksPerServer;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minProcessingTime;
    private int maxProcessingTime;
    private SelectionPolicy selectionPolicy;

    public SimulationConfig() {
        timeLimit = 5;
        maxTasksPerServer = 100;
        minArrivalTime = 0;
        maxArrivalTime = 5;
        minProcessingTime = 2;
        maxProcessingTime = 10;
        numberOfServers = 3;
        numberOfClients = 5;
        selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    }

    public SimulationConfig(int numberOfClients, int numberOfServers, int timeLimit, int maxTasksPerServer, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime, SelectionPolicy selectionPolicy) {
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        this.timeLimit = timeLimit;
        this.maxTasksPerServer = maxTasksPerServer;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.selectionPolicy = selectionPolicy;
    }

    @Override
    public int getNumberOfServers() {
        return numberOfServers;
    }

    public SimulationConfig setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
        return this;
    }

    @Override
    public int getNumberOfClients() {
        return numberOfClients;
    }

    public SimulationConfig setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
        return this;
    }

    @Override
    public int getTimeLimit() {
        return timeLimit;
    }

    public SimulationConfig setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    @Override
    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public SimulationConfig setMaxTasksPerServer(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
        return this;
    }

    @Override
    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public SimulationConfig setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
        return this;
    }

    @Override
    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public SimulationConfig setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
        return this;
    }

    @Override
    public int getMinProcessingTime() {
        return minProcessingTime;
    }

    public SimulationConfig setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
        return this;
    }

    @Override
    public int getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public SimulationConfig setMaxProcessingTime(int maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
        return this;
    }

    @Override
    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    public SimulationConfig setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
        return this;
    }
}
