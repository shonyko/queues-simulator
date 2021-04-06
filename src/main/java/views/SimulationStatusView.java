package views;

import interfaces.SimulationConfiguration;
import models.SimulationResult;
import views.utils.GridBagConstraintsFactory;

import javax.swing.*;
import java.awt.*;

public class SimulationStatusView extends JFrame {
    //<editor-fold desc="Panels">
    private JPanel mainContentPanel;
    private JPanel topPanel;
    private JPanel statusPanel;
    private JPanel avgWaitingTimePanel;
    private JPanel avgProcessingTimePanel;
    private JPanel peakHourPanel;
    private JPanel logPanel;
    private JScrollPane textAreaPanel;
    //</editor-fold>

    //<editor-fold desc="Labels">
    private JLabel statusLabel;
    private JLabel avgWaitingLabel;
    private JLabel avgProcessLabel;
    private JLabel peakHourLabel;

    private JTextArea textArea;
    //</editor-fold>

    public SimulationStatusView(String title) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        setUpUI();

        this.setTitle(title);
        this.setSize(600, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        initLabels();
        initPanels();
    }

    private void initPanels() {
        mainContentPanel        = new JPanel(new GridBagLayout());
        topPanel                = new JPanel(new GridLayout(1, 0));
        statusPanel             = new JPanel(new GridLayout(1, 1));
        avgWaitingTimePanel     = new JPanel(new GridLayout(1, 1));
        avgProcessingTimePanel  = new JPanel(new GridLayout(1, 1));
        peakHourPanel           = new JPanel(new GridLayout(1, 1));
        logPanel                = new JPanel(new GridLayout(1, 1));
        textAreaPanel           = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        statusPanel             .setBorder(BorderFactory.createTitledBorder("Status"));
        avgWaitingTimePanel     .setBorder(BorderFactory.createTitledBorder("Avg. Wait"));
        avgProcessingTimePanel  .setBorder(BorderFactory.createTitledBorder("Avg. Process"));
        peakHourPanel           .setBorder(BorderFactory.createTitledBorder("Peak Hour"));
        logPanel                .setBorder(BorderFactory.createTitledBorder("Logs"));
    }

    private void initLabels() {
        statusLabel = new JLabel("Running");
        statusLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        statusLabel.setForeground(new Color(34,139,34));

        avgWaitingLabel = new JLabel("∞");
        avgWaitingLabel.setFont(new Font("Verdana", Font.BOLD, 12));

        avgProcessLabel = new JLabel("∞");
        avgProcessLabel.setFont(new Font("Verdana", Font.BOLD, 12));

        peakHourLabel = new JLabel("-1");
        peakHourLabel.setFont(new Font("Verdana", Font.BOLD, 12));

        textArea = new JTextArea("Simulation starting...\n\n");
        textArea.setEditable(false);
    }

    private void setUpUI() {
        setUpPanels();
        setUpLabels();
    }

    private void setUpPanels() {
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.add(mainContentPanel);

        logPanel.add(textAreaPanel);

        topPanel.add(statusPanel            , GridBagConstraintsFactory.getConstraints(0, 0));
        topPanel.add(avgWaitingTimePanel    , GridBagConstraintsFactory.getConstraints(0, 1));
        topPanel.add(avgProcessingTimePanel , GridBagConstraintsFactory.getConstraints(0, 2));
        topPanel.add(peakHourPanel          , GridBagConstraintsFactory.getConstraints(0, 3));

        mainContentPanel.add(topPanel, GridBagConstraintsFactory.getConstraints(0, 0, 1, 0.1));
        mainContentPanel.add(logPanel, GridBagConstraintsFactory.getConstraints(1, 0, 1, 0.9));
    }

    private void setUpLabels() {
        statusPanel             .add(statusLabel);
        avgWaitingTimePanel     .add(avgWaitingLabel);
        avgProcessingTimePanel  .add(avgProcessLabel);
        peakHourPanel           .add(peakHourLabel);
    }

    public void addLog(String log) {
        textArea.append(log);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public void setParams(SimulationConfiguration config) {
        textArea.append("Simulation successfully started with the following parameters:\n");
        textArea.append(String.format("No. clients: %d; No. servers: %d\n", config.getNumberOfClients(), config.getNumberOfServers()));
        textArea.append(String.format("Time limit: %d; Max load: %d\n", config.getTimeLimit(), config.getMaxTasksPerServer()));
        textArea.append(String.format("Min arrival time: %d; Max arrival time: %d\n", config.getMinArrivalTime(), config.getMaxArrivalTime()));
        textArea.append(String.format("Min processing time: %d; Max processing time: %d\n", config.getMinProcessingTime(), config.getMaxProcessingTime()));
        textArea.append("Selection policy: " + config.getSelectionPolicy().name() + "\n\n");
    }

    public void setResults(SimulationResult result) {
        setAvgWaitTime(result.getAvgWaitTime());
        setAvgProcessTime(result.getAvgProcessTime());
        setPeakHour(result.getPeakHour());
    }

    public void setAvgWaitTime(double avg) {
        avgWaitingLabel.setText(String.format("%.2f", avg));
    }

    public void setAvgProcessTime(double avg) {
        avgProcessLabel.setText(String.format("%.2f", avg));
    }

    public void setPeakHour(int time) {
        peakHourLabel.setText(String.format("%d", time));
    }
}
