package Services;

import Objects.ObjectData;
import Objects.UserData;
import Repositories.UserRepository;

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

    private UserData userData;

    private String sessionNumber, sessionToken;

    public ClientHandler(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    this.objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
    this.objectIn = new ObjectInputStream(clientSocket.getInputStream());

    }

    @Override
    public void run(){

        userData = new UserData();
        if (userData.getSessionNumber().contains("00000")) {
            while(true) {
                boolean checkGenerator = UserRepository.getInstance().searchEquals(sessionNumber = SessionGenerator.getSesionNumber());
                if(!checkGenerator){
                   // user.setSesionToken(SessionGenerator.getSessionToken());;
                    userData.setSessionNumber(sessionNumber);
                    UserRepository.getInstance().addUser(sessionNumber, userData);
                    break;
                }
            }
        }
        ComClientService con = new ComClientService(objectIn,objectOut,sessionNumber,clientSocket);
        con.start();
        int i=0;
        while(true){
            i++;
           CommunicationServices.recive(objectIn,UserRepository.getInstance().getUser(sessionNumber));
           CommandManager.manage(userData);
            //ManagerLast.decision(UserRepository.getInstance().getUser(sessionNumber));

            System.out.println(i);
        }


    }


}
