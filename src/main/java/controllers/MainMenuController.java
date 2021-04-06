package controllers;

import models.SimulationConfig;
import models.viewmodels.MainMenuViewModel;
import views.MainMenuView;

public class MainMenuController {
    private final MainMenuViewModel model;
    private final MainMenuView view;

    public MainMenuController(MainMenuViewModel model, MainMenuView view) {
        this.model = model;
        this.view = view;

        setUpUserEvents();
    }

    private void setUpUserEvents() {
        view.addBtnActionListener((e) -> {
            try {
                var config = new SimulationConfig();
                config  .setNumberOfClients(Integer.parseInt(view.getNumberOfClientsText()))
                        .setNumberOfServers(Integer.parseInt(view.getNumberOfServersText()))
                        .setTimeLimit(Integer.parseInt(view.getTimeLimitText()))
                        .setMaxTasksPerServer(Integer.parseInt(view.getMaxLoadPerServerText()))
                        .setMinArrivalTime(Integer.parseInt(view.getMinArrivalTimeText()))
                        .setMaxArrivalTime(Integer.parseInt(view.getMaxArrivalTimeText()))
                        .setMinProcessingTime(Integer.parseInt(view.getMinProcessingTimeText()))
                        .setMaxProcessingTime(Integer.parseInt(view.getMaxProcessingTimeText()))
                        .setSelectionPolicy(view.getSelectionPolicy());

                var simulation = model.createSimulation(config);
                var thread = new Thread(simulation);
                thread.start();
            }
            catch (Exception ex) {
                view.showError("Only whole numbers are allowed as input!");
            }
        });
    }
}
