package fr.tln.univ.communication;

public class EmailServiceImp implements MessageService{
    @Override
    public void sendMessage(String msg) {
        System.out.println("Send Msg with Email");
    }
}
