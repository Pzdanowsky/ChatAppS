import Objects.MessageObject;
import Objects.ObjectData;

import java.io.File;

public class CommandServices {

    private static ObjectData decision;
    private static int i = 0;

    public static void decision(User user) {

        ObjectData send = new ObjectData();
        decision = DataReciveRepository.getInstance().getObjectData(user.getSesionNumber());
        //UserRepository.getInstance().updateUser(user);
        if(decision != null) {

            if(decision.getCommand().equals("message")) {
                send.setCommand("message");
                MessageObject messageObject = new MessageObject();
                //if(decision.getCommand().equals("message")){
                messageObject.setToMessage(decision.getMessageObject().getToMessage());
                //send.setTo(decision.getTo());
                if (decision.getMessageObject().getData() == null) {
                    messageObject.setData("empty");
                    //send.setData("empty");
                } else {
                    messageObject.setData(decision.getMessageObject().getData());
                    //send.setData(decision.getData());
                }

                User useReciver = UserRepository.getInstance().searchUser(decision);

                if (useReciver == null) {
                    System.out.println("Dane zapisane do tabeli czatu w bazie");
                } else {
                    System.out.println("Do: " + useReciver.getUsername() + " od: " + user.getUsername());
                    send.setSesionToken(useReciver.getSesionToken());
                    send.setSessionNumber(useReciver.getSesionNumber());
                }
                send.setMessageObject(messageObject);
                DataSendRepository.getInstance().addDataSend(send);
            }

            if(decision.getCommand().equals("file")){
                send.setCommand("file");

                if(decision.getFileObject() != null) {

                    send.setFileObject(decision.getFileObject());
                }else{
                    System.out.println("Nie ma pliku mordo");

                }

                User useReciver = UserRepository.getInstance().searchUser(decision);

                if (useReciver == null) {
                    System.out.println("Dane zapisane do tabeli czatu w bazie");
                } else {
                    System.out.println("Do: " + useReciver.getUsername() + " od: " + user.getUsername());
                    send.setSesionToken(useReciver.getSesionToken());
                    send.setSessionNumber(useReciver.getSesionNumber());
                }

                DataSendRepository.getInstance().addDataSend(send);

            }
           // DataSendRepository.getInstance().addDataSend(send);
        }else{
            i++;
            System.out.println("morda");
        }

    }
}
