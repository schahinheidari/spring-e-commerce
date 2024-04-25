package fr.tln.univ.communication;

public class EmailServiceInjector implements MessageServiceInjector{
    @Override
    public DecisionService getDecisionService() {
        return new MyDIApplication(new EmailServiceImp());
    }
}
