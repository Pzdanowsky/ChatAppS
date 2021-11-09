import java.io.*;
import java.net.*;
import java.util.Random;

public class CommunicationServices {

   private static ObjectData objectDataRecive;
   private static ObjectData objectDataSend;
   private static Socket clientSocket;


    public static void recive(ObjectInputStream objectIn,User user) {

        try {
            objectDataRecive = (ObjectData) objectIn.readObject();


            if(objectDataRecive !=null) {
                if(!checkUserData(objectDataRecive,user)) {
                    objectDataRecive.setSessionNumber(user.getSesionNumber());
                    objectDataRecive.setSesionToken(user.getSesionToken());
                    user.setUsername(objectDataRecive.getUsername());
                   //UserRepository.getInstance().updateUser(user);

                }

                DataReciveRepository.getInstance().addDataRecive(objectDataRecive);
                objectDataRecive = null;


            }

        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
            System.out.println("Bład odbioru: " + clientSocket.getLocalPort());
        }

    }

    public static void send(ObjectOutputStream objectOut, User user){
       objectDataSend = DataSendRepository.getInstance().getObjectData(user.getSesionNumber());
       if(objectDataSend == null){
          // System.out.println("Pusty");
       }else {
           try {

               System.out.println("Send: "+ objectDataSend.getData());
               objectOut.writeObject(objectDataSend);
               objectOut.reset();
               //System.out.println(objectDataSend.getData());

           } catch (IOException ex) {
               ex.printStackTrace();
               System.out.println("Bład wysyłania " + clientSocket.getLocalPort());
           }
       }
    }

    public static boolean checkUserData(ObjectData objectDataRecive, User user){
        if(objectDataRecive.getSessionNumber().equals("00000")){

            return false;
        }else if(user.checkEqualsSesion(objectDataRecive.getSesionToken())){
            System.out.println("Dobry");
            return true;
        }
        return true;
    }


}