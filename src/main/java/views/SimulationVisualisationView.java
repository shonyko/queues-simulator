package views;

import interfaces.SimulationConfiguration;
import models.Task;
import views.utils.GridBagConstraintsFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SimulationVisualisationView extends JFrame {

    private static final int ICON_SIZE = 40;

    //<editor-fold desc="Panels">
    private JPanel mainContentPanel;
    private JScrollPane leftClientsScrollPane;
    private JPanel leftClientsPanel;
    private JScrollPane queuesScrollPane;
    private JPanel queuesPanel;
    //</editor-fold>

    SimulationConfiguration config;
    List<List<JLabel>> queues;
    List<JLabel> leftClients;

    public SimulationVisualisationView(String title, SimulationConfiguration config) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.config = config;

        initComponents();
        setUpUI();

        this.setTitle(title);
        this.setSize(600, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        initPanels();

        initScrollPanes();
    }

    private void initPanels() {
        mainContentPanel        = new JPanel(new GridBagLayout());
        leftClientsPanel        = new JPanel(new GridLayout(0, 1));
        queuesPanel             = new JPanel(new GridLayout(0, config.getNumberOfServers()));

        leftClientsPanel.setBorder(BorderFactory.createTitledBorder("Clients Left"));
        queuesPanel.setBorder(BorderFactory.createTitledBorder("Queues"));

        var clientIcon = new ImageIcon("res/HumanIcon.png").getImage();
        var clientImg = new ImageIcon(scaleImage(clientIcon, ICON_SIZE, ICON_SIZE));

        var cartIcon = new ImageIcon("res/CartIcon.png").getImage();
        var cartImg = new ImageIcon(scaleImage(cartIcon, ICON_SIZE, ICON_SIZE));

        queues = new ArrayList<>();
        leftClients = new ArrayList<>();

        for(int i = 0; i < config.getNumberOfClients(); i++) {
            var label = new JLabel(clientImg);
            label.setBorder(null);
            leftClientsPanel.add(label);

            leftClients.add(label);
        }

        for(int i = 0; i < config.getNumberOfServers(); i++) {
            var label = new JLabel(cartImg);
            label.setBorder(BorderFactory.createTitledBorder(Integer.toString(i + 1)));
            queuesPanel.add(label);

            var list = new ArrayList<JLabel>();
            queues.add(list);
        }

        for(int i = 0; i < config.getNumberOfServers() * config.getMaxTasksPerServer(); i++) {
            var label = new JLabel(clientImg);
            label.setBorder(null);
            queuesPanel.add(label);

            queues.get(i % queues.size()).add(label);
        }
    }

    private void initScrollPanes() {
        leftClientsScrollPane = new JScrollPane(leftClientsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        queuesScrollPane = new JScrollPane(queuesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void setUpUI() {
        setUpPanels();
    }

    private void setUpPanels() {
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.add(mainContentPanel);

        mainContentPanel.add(leftClientsScrollPane, GridBagConstraintsFactory.getConstraints(0, 0, 15.0, 1));
        mainContentPanel.add(queuesScrollPane, GridBagConstraintsFactory.getConstraints(0, 1, 85.0, 1));
    }

    private Image scaleImage(Image img, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();

        return resizedImg;
    }

    public void updateLeftClients(List<Task> clients) {
        int size = clients.size();
        leftClients.stream().reduce(0, (cnt, clientIcon) -> {
            SwingUtilities.invokeLater(() -> clientIcon.setVisible(cnt < size));
            return  cnt + 1;
        }, Integer::sum);
    }

    public void updateQueue(int index, List<Task> clients) {
        int size = clients.size();
        queues.get(index).stream().reduce(0, (cnt, clientIcon) -> {
            SwingUtilities.invokeLater(() -> clientIcon.setVisible(cnt < size));
            return  cnt + 1;
        }, Integer::sum);
    }
}
