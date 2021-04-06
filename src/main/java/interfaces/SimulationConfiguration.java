package interfaces;

import models.enums.SelectionPolicy;

public interface SimulationConfiguration {
    int getNumberOfClients();
    int getNumberOfServers();
    int getTimeLimit();
    int getMaxTasksPerServer();
    int getMinArrivalTime();
    int getMaxArrivalTime();
    int getMinProcessingTime();
    int getMaxProcessingTime();


    SelectionPolicy getSelectionPolicy();
}
