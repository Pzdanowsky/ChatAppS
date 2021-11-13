package Services;

import Objects.ObjectData;
import Objects.User;
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

    private User user;

    private String sessionNumber, sessionToken;

    public ClientHandler(Socket clientSocket) throws IOException {
    this.clientSocket = clientSocket;
    this.objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
    this.objectIn = new ObjectInputStream(clientSocket.getInputStream());

    }

    @Override
    public void run(){

        user = new User();
        if (user.getSesionNumber().contains("00000")) {
            while(true) {
                boolean checkGenerator = UserRepository.getInstance().searchEquals(sessionNumber = SessionGenerator.getSesionNumber());
                if(!checkGenerator){
                   // user.setSesionToken(SessionGenerator.getSessionToken());;
                    user.setSesionNumber(sessionNumber);
                    UserRepository.getInstance().addUser(sessionNumber,user);
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
           CommandManager.manage(user);
            //ManagerLast.decision(UserRepository.getInstance().getUser(sessionNumber));

            System.out.println(i);
        }


    }


}
