import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket clientSocket;

    private ObjectOutputStream objectOut;

    private ObjectInputStream objectIn;

    private ObjectData objectDataSend;

    private ObjectData objectDataRecive;


    ClientHandler(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    this.objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
    this.objectIn = new ObjectInputStream(clientSocket.getInputStream());

    }

    @Override
    public void run(){

        int i=0;
        while(true){
            i++;
            CommunicationServices.recive(objectIn);
            CommandServices.decision(i);
            CommunicationServices.send(objectOut);

        }


    }


}
