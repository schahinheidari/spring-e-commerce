package fr.tln.univ.communication;

public class MyDIApplication implements DecisionService{
    private MessageService service;

    public MyDIApplication(MessageService service) {
        this.service = service;
    }

    @Override
    public void processMessage(String msg) {
        service.sendMessage(msg);
    }
}
