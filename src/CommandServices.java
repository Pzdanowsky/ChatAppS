import java.io.ObjectInputStream;

public class CommandServices {

    private static ObjectData decision;

    public static void decision(User user) {
        ObjectData send = new ObjectData();
        decision = DataReciveRepository.getInstance().getObjectData(user.getSesionNumber());
        //UserRepository.getInstance().updateUser(user);

        //if(decision.getCommand().equals("message")){
            send.setTo(decision.getTo());
            if(decision.getData() == null) {
                send.setData("empty");
            }else{
                send.setData(decision.getData());
            }

            String adres = decision.getTo();
        System.out.println(adres);
             User useReciver = UserRepository.getInstance().searchUser(decision);

           if(useReciver == null){
                System.out.println("dupa");
            }else{
                System.out.println("Mom co do "+ useReciver.getUsername()+ " od "+user.getUsername());
               send.setSesionToken(useReciver.getSesionToken());
               send.setSessionNumber(useReciver.getSesionNumber());
            }

        DataSendRepository.getInstance().addDataSend(send);
    }
}
