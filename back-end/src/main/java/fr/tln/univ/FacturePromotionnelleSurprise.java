package fr.tln.univ;

import java.util.Random;

public class FacturePromotionnelleSurprise implements InvoiceCalculator {
    @Override
    public double calculateTotalAmount(double originalAmount) {
        double reductionPercentage = generateRandomPercentage(10, 50);
        double reducedAmount = originalAmount - (originalAmount * (reductionPercentage / 100));
        return Math.max(reducedAmount, 0);
    }

    private double generateRandomPercentage(int min, int max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }
}
