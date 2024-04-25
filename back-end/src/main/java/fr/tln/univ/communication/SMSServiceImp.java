package fr.tln.univ.communication;

public class SMSServiceImp implements MessageService
{
    @Override
    public void sendMessage(String msg) {
        System.out.println("Send Msg with SMS");
    }
}
