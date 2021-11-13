package Services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Objects.ObjectData;
import Repositories.DataSendRepository;

public class ComClientService extends Thread{

    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    private String sessionNumber;
    private ObjectData objectDataSend;
    private Socket clientSocket;

    ComClientService(ObjectInputStream objectIn, ObjectOutputStream objectOut, String sessionNumber, Socket clientSocket){
        this.objectIn = objectIn;
        this.objectOut = objectOut;
        this.sessionNumber = sessionNumber;
        this.clientSocket = clientSocket;
    }


        @Override
        public void run(){
    int i=0;
        while(true) {
            objectDataSend = DataSendRepository.getInstance().getObjectData(sessionNumber);
            if (objectDataSend != null) {

                try {

                    //System.out.println("Send: " + objectDataSend.getMessageObject().getData());
                    objectOut.writeObject(objectDataSend);
                    objectOut.reset();
                    //System.out.println(objectDataSend.getData());

                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Bład wysyłania " + clientSocket.getLocalPort());
                }
            }else{
                i++;
            }
        }
        }

}



