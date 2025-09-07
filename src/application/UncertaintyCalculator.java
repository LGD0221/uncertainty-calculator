package application;

import java.util.List;

public class UncertaintyCalculator {
    public double calculateUncertainty(List<Double> measurements) {
        int n = measurements.size();
        if (n <= 1) {
            throw new IllegalArgumentException("至少输入两个数据");
        }

        double sum = 0.0;
        for (double measurement : measurements) {
            sum += measurement;
        }
        double mean = sum / n;

        double squaredDeviationsSum = 0.0;
        for (double measurement : measurements) {
            double deviation = measurement - mean;
            squaredDeviationsSum += deviation * deviation;
        }

        double variance = squaredDeviationsSum / (n - 1);
        return Math.sqrt(variance);
    }
}
