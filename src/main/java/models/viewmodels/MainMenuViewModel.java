package models.viewmodels;

import businessLogic.SimulationManager;
import interfaces.SimulationConfiguration;
import utils.FileLogger;
import views.SimulationStatusView;
import views.SimulationVisualisationView;

public class MainMenuViewModel {
    private String path = ".\\";
    private int simulationNb;

    public MainMenuViewModel() {
        simulationNb = 0;
    }

    public MainMenuViewModel(String path) {
        this.path = path;
    }

    public SimulationManager createSimulation(SimulationConfiguration config) {
        var view = new SimulationStatusView("Simulation no. " + simulationNb);
        var secondaryView = new SimulationVisualisationView("Simulation no. " + simulationNb, config);

        var path = this.path + "log";
        if(simulationNb > 0) {
            path += simulationNb;
        }
        path += ".txt";
        var logger = new FileLogger(path);

        simulationNb++;

        return new SimulationManager(config, view, secondaryView, logger);
    }
}
