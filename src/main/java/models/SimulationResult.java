package models;

public class SimulationResult {
    private double avgWaitTime;
    private double avgProcessTime;
    private int maxClientsNb;
    private int peakHour;

    public SimulationResult() {
        peakHour = -1;
    }

    public SimulationResult(double avgWaitTime, double avgProcessTime, int maxClientsNb, int peakHour) {
        this.avgWaitTime = avgWaitTime;
        this.avgProcessTime = avgProcessTime;
        this.maxClientsNb = maxClientsNb;
        this.peakHour = peakHour;
    }

    public double getAvgWaitTime() {
        return avgWaitTime;
    }

    public void setAvgWaitTime(double avgWaitTime) {
        this.avgWaitTime = avgWaitTime;
    }

    public double getAvgProcessTime() {
        return avgProcessTime;
    }

    public void setAvgProcessTime(double avgProcessTime) {
        this.avgProcessTime = avgProcessTime;
    }

    public int getMaxClientsNb() {
        return maxClientsNb;
    }

    public void setMaxClientsNb(int maxClientsNb) {
        this.maxClientsNb = maxClientsNb;
    }

    public int getPeakHour() {
        return peakHour;
    }

    public void setPeakHour(int peakHour) {
        this.peakHour = peakHour;
    }

    @Override
    public String toString() {
        var avgWaitMsg = String.format("Average waiting time: %.2f", getAvgWaitTime());
        var avgProcessMsg = String.format("Average processing time: %.2f", getAvgProcessTime());
        var peakHourMsg = "Peak hour: " + getPeakHour();

        var sb = new StringBuilder("\nSimulation terminated successfully with the following results:\n")
                .append(avgWaitMsg).append('\n')
                .append(avgProcessMsg).append('\n')
                .append(peakHourMsg);

        return sb.toString();
    }
}
