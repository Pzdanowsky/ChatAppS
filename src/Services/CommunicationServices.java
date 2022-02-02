package Services;

import Objects.ObjectData;
import Objects.UserData;
import Repositories.DataReciveRepository;
import Repositories.DataSendRepository;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommunicationServices {

   private static ObjectData objectDataRecive;
   private static ObjectData objectDataSend;
   private static Socket clientSocket;
   private static int i = 0;


    public static void recive(ObjectInputStream objectIn, UserData userData) throws SQLException {

        try {
            objectDataRecive = (ObjectData) objectIn.readObject();
             if(objectDataRecive !=null) {

                 objectDataRecive.getUserData().setSessionNumber(userData.getSessionNumber());
                DataReciveRepository.getInstance().addDataRecive(objectDataRecive);
                objectDataRecive = null;

            }

        } catch (ClassNotFoundException | IOException ex) {
            System.out.println("Bład odbioru: " + clientSocket.getLocalPort());
        }

    }

    public static void send(ObjectOutputStream objectOut, UserData userData){
       objectDataSend = DataSendRepository.getInstance().getObjectData(userData.getSessionNumber());
       if(objectDataSend == null){
           System.out.println("Pusto mordo");
       }else {
           try {

              // System.out.println("Send: "+ objectDataSend.getData());
               objectOut.writeObject(objectDataSend);
               objectOut.reset();
               //System.out.println(objectDataSend.getData());

           } catch (IOException ex) {
               ex.printStackTrace();
               System.out.println("Bład wysyłania " + clientSocket.getLocalPort());
           }
       }
    }

    public static boolean checkUserData(ObjectData objectDataRecive, UserData userData){
        if(objectDataRecive.getUserData().getSessionNumber().equals("00000")){

            return false;
        }else if(userData.checkEqualsSesion(objectDataRecive.getUserData().getSessionToken())){
            System.out.println("Dobry");
            return true;
        }
        return true;
    }


}