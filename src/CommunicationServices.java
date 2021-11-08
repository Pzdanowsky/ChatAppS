import java.io.*;
import java.net.*;

public class CommunicationServices {

   private static ObjectData objectDataRecive;
   private static ObjectData objectDataSend;
   private static Socket clientSocket;


    public static void recive(ObjectInputStream objectIn) {
            //ObjectData obj = new ObjectData();
        try {
            objectDataRecive = (ObjectData) objectIn.readObject();
            System.out.println(objectDataRecive.getData());
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