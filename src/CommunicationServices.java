import java.io.*;
import java.net.*;
import java.util.Random;

public class CommunicationServices {

   private static ObjectData objectDataRecive;
   private static ObjectData objectDataSend;
   private static Socket clientSocket;


    public static void recive(ObjectInputStream objectIn,User user) {
            //ObjectData obj = new ObjectData();
        try {
            objectDataRecive = (ObjectData) objectIn.readObject();
            //System.out.println(objectDataRecive.getData());

            if (objectDataRecive.getSessionNumber().contains("00000")) {
                user = new User(objectDataRecive.getUsername());
                UserRepository.getInstance().addUser(objectDataRecive.getSessionNumber(),user );
                Random rn = new Random();
                String s = String.valueOf(rn.nextInt(10) + 1);
                user.setTag(s);
            }

            DataReciveRepository.getInstance().addDataRecive(objectDataRecive);
            objectDataRecive = null;

        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
            System.out.println("Bład odbioru: " + clientSocket.getLocalPort());
        }

    }

    public static void send(ObjectOutputStream objectOut){
       objectDataSend = DataSendRepository.getInstance().getObjectData("serwer");
        try {
            //System.out.println("Send");
            objectOut.writeObject(objectDataSend);
            System.out.println(objectDataSend.getData());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Bład wysyłania "+clientSocket.getLocalPort());
        }
    }


}