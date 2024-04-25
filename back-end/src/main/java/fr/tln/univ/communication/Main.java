package fr.tln.univ.communication;

public class Main {
    public static void main(String[] args) {
        MessageServiceInjector messageServiceInjector = null;

        DecisionService decisionService = null;
        messageServiceInjector = new EmailServiceInjector();
        decisionService = messageServiceInjector.getDecisionService();

        decisionService.processMessage("Salam");

    }
}
