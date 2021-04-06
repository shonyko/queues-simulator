package views;

import models.enums.SelectionPolicy;
import views.utils.GridBagConstraintsFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {
    //<editor-fold desc="Panels">
    private JPanel mainContentPanel;
    private JPanel numberOfClientsPanel;
    private JPanel numberOfServersPanel;
    private JPanel timeLimitPanel;
    private JPanel maxLoadPerServerPanel;
    private JPanel minArrivalTimePanel;
    private JPanel maxArrivalTimePanel;
    private JPanel minProcessingTimePanel;
    private JPanel maxProcessingTimePanel;
    private JPanel selectionPolicyPanel;
    //</editor-fold>

    //<editor-fold desc="TextFields">
    private TextField numberOfClientsTextField;
    private TextField numberOfServersTextField;
    private TextField timeLimitTextField;
    private TextField maxLoadPerServerTextField;
    private TextField minArrivalTimeTextField;
    private TextField maxArrivalTimeTextField;
    private TextField minProcessingTimeTextField;
    private TextField maxProcessingTimeTextField;
    //</editor-fold>

    private JComboBox<SelectionPolicy> selectionPolicyComboBox;
    private Button startSimulationBtn;

    public MainMenuView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        setUpUI();

        this.setTitle("Queue Simulator");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        initPanels();
        initTextFields();
        selectionPolicyComboBox = new JComboBox<>();
        startSimulationBtn = new Button("Start Simulation!");
    }

    private void initPanels() {
        mainContentPanel        = new JPanel(new GridBagLayout());
        numberOfClientsPanel    = new JPanel(new GridLayout(1, 1));
        numberOfServersPanel    = new JPanel(new GridLayout(1, 1));
        timeLimitPanel          = new JPanel(new GridLayout(1, 1));
        maxLoadPerServerPanel   = new JPanel(new GridLayout(1, 1));
        minArrivalTimePanel     = new JPanel(new GridLayout(1, 1));
        maxArrivalTimePanel     = new JPanel(new GridLayout(1, 1));
        minProcessingTimePanel  = new JPanel(new GridLayout(1, 1));
        maxProcessingTimePanel  = new JPanel(new GridLayout(1, 1));
        selectionPolicyPanel    = new JPanel(new GridLayout(1, 1));

        numberOfClientsPanel    .setBorder(BorderFactory.createTitledBorder("No. clients"));
        numberOfServersPanel    .setBorder(BorderFactory.createTitledBorder("No. servers"));
        timeLimitPanel          .setBorder(BorderFactory.createTitledBorder("Time limit"));
        maxLoadPerServerPanel   .setBorder(BorderFactory.createTitledBorder("Max load"));
        minArrivalTimePanel     .setBorder(BorderFactory.createTitledBorder("Min arrival time"));
        maxArrivalTimePanel     .setBorder(BorderFactory.createTitledBorder("Max arrival time"));
        minProcessingTimePanel  .setBorder(BorderFactory.createTitledBorder("Min processing time"));
        maxProcessingTimePanel  .setBorder(BorderFactory.createTitledBorder("Max processing time"));
        selectionPolicyPanel    .setBorder(BorderFactory.createTitledBorder("Selection policy"));
    }

    private void initTextFields() {
        numberOfClientsTextField    = new TextField("4");
        numberOfServersTextField    = new TextField("2");
        timeLimitTextField          = new TextField("60");
        maxLoadPerServerTextField   = new TextField("100");
        minArrivalTimeTextField     = new TextField("2");
        maxArrivalTimeTextField     = new TextField("30");
        minProcessingTimeTextField  = new TextField("2");
        maxProcessingTimeTextField  = new TextField("4");

        var font = new Font("Verdana", Font.BOLD, 12);
        numberOfClientsTextField    .setFont(font);
        numberOfServersTextField    .setFont(font);
        timeLimitTextField          .setFont(font);
        maxLoadPerServerTextField   .setFont(font);
        minArrivalTimeTextField     .setFont(font);
        maxArrivalTimeTextField     .setFont(font);
        minProcessingTimeTextField  .setFont(font);
        maxProcessingTimeTextField  .setFont(font);
    }

    private void setUpUI() {
        setUpPanels();
        setUpInputFields();
    }

    private void setUpPanels() {
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.add(mainContentPanel);
        mainContentPanel.add(numberOfClientsPanel   , GridBagConstraintsFactory.getConstraints(1, 0));
        mainContentPanel.add(numberOfServersPanel   , GridBagConstraintsFactory.getConstraints(1, 1));
        mainContentPanel.add(timeLimitPanel         , GridBagConstraintsFactory.getConstraints(3, 0));
        mainContentPanel.add(maxLoadPerServerPanel  , GridBagConstraintsFactory.getConstraints(3, 1));
        mainContentPanel.add(minArrivalTimePanel    , GridBagConstraintsFactory.getConstraints(5, 0));
        mainContentPanel.add(maxArrivalTimePanel    , GridBagConstraintsFactory.getConstraints(5, 1));
        mainContentPanel.add(minProcessingTimePanel , GridBagConstraintsFactory.getConstraints(7, 0));
        mainContentPanel.add(maxProcessingTimePanel , GridBagConstraintsFactory.getConstraints(7, 1));
        mainContentPanel.add(selectionPolicyPanel   , GridBagConstraintsFactory.getConstraints(9, 0, 2, 1));
    }

    private void setUpInputFields() {
        numberOfClientsPanel.add(numberOfClientsTextField);
        numberOfServersPanel.add(numberOfServersTextField);
        timeLimitPanel.add(timeLimitTextField);
        maxLoadPerServerPanel.add(maxLoadPerServerTextField);
        minArrivalTimePanel.add(minArrivalTimeTextField);
        maxArrivalTimePanel.add(maxArrivalTimeTextField);
        minProcessingTimePanel.add(minProcessingTimeTextField);
        maxProcessingTimePanel.add(maxProcessingTimeTextField);
        selectionPolicyPanel.add(selectionPolicyComboBox);

        selectionPolicyComboBox.addItem(SelectionPolicy.SHORTEST_TIME);
        selectionPolicyComboBox.addItem(SelectionPolicy.SHORTEST_QUEUE);

        var panel = new JPanel(new FlowLayout());
        panel.add(startSimulationBtn);
        mainContentPanel.add(panel, GridBagConstraintsFactory.getConstraints(10, 0, 2, 1));
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public String getNumberOfClientsText() {
        return numberOfClientsTextField.getText();
    }

    public String getNumberOfServersText() {
        return numberOfServersTextField.getText();
    }

    public String getTimeLimitText() {
        return timeLimitTextField.getText();
    }

    public String getMaxLoadPerServerText() {
        return maxLoadPerServerTextField.getText();
    }

    public String getMinArrivalTimeText() {
        return minArrivalTimeTextField.getText();
    }

    public String getMaxArrivalTimeText() {
        return maxArrivalTimeTextField.getText();
    }

    public String getMinProcessingTimeText() {
        return minProcessingTimeTextField.getText();
    }

    public String getMaxProcessingTimeText() {
        return maxProcessingTimeTextField.getText();
    }

    public SelectionPolicy getSelectionPolicy() {
        return (SelectionPolicy) selectionPolicyComboBox.getSelectedItem();
    }

    public void addBtnActionListener(ActionListener al) {
        startSimulationBtn.addActionListener(al);
    }
}
