package views.utils;

import java.awt.*;

public class GridBagConstraintsFactory {
    private final static int DEFAULT_FILL = GridBagConstraints.BOTH;

    private GridBagConstraintsFactory() {

    }

    public static GridBagConstraints getConstraints() {
        return getConstraints(DEFAULT_FILL, 0, 0, 1, 1, 1, 1);
    }

    public static GridBagConstraints getConstraints(int row, int col) {
        return getConstraints(DEFAULT_FILL, row, col, 1, 1, 1, 1);
    }

    public static GridBagConstraints getConstraints(int row, int col, double weightx, double weighty) {
        return getConstraints(DEFAULT_FILL, row, col, weightx, weighty, 1, 1);
    }

    public static GridBagConstraints getConstraints(int row, int col, int gridwidth, int gridheight) {
        return getConstraints(DEFAULT_FILL, row, col, 1, 1, gridwidth, gridheight);
    }

    public static GridBagConstraints getConstraints(int row, int col, double weightx, double weighty, int gridwidth, int gridheight) {
        return getConstraints(DEFAULT_FILL, row, col, weightx, weighty, gridwidth, gridheight);
    }

    public static GridBagConstraints getConstraints(int fill, int row, int col, double weightx, double weighty, int gridwidth, int gridheight) {
        var result = new GridBagConstraints();

        result.fill = fill;
        result.gridx = col;
        result.gridy = row;
        result.weightx = weightx;
        result.weighty = weighty;
        result.gridwidth = gridwidth;
        result.gridheight = gridheight;

        return result;
    }
}
