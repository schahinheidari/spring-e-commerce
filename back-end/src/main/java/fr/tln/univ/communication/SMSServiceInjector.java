package fr.tln.univ.communication;

public class SMSServiceInjector implements MessageServiceInjector{
    @Override
    public DecisionService getDecisionService() {
        return new MyDIApplication(new SMSServiceImp());
    }
}
