package Services.ReqStrategy;

import Objects.ObjectData;
import Objects.UserData;
import Repositories.UserRepository;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;
import Services.SessionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    private static UserData userData;

    private ObjectData objectData;


    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {
        UserData sendUserData = new UserData();

        ObjectData objectDataSend = new ObjectData();
        sendUserData.setSessionNumber(userData.getSessionNumber());

        objectDataSend.setCommand("00001");
        if(objectData == null){
            System.out.println("Pusty obiekt");
            objectDataSend.setAuthenticated(false);

        }else if(!objectData.getUserData().getUsername().isEmpty() && !objectData.getUserData().getPassword().isEmpty() ){



            myConn = DatabaseConnectionService.getConn();
            try {
                PreparedStatement pstat = myConn.prepareStatement("SELECT userID FROM users WHERE login =? AND password =?");
                pstat.setString(1,objectData.getUserData().getUsername());
                pstat.setString(2,objectData.getUserData().getPassword());

                myRs = pstat.executeQuery();

                if(!myRs.next()){
                    objectDataSend.setAuthenticated(false);
                    System.out.println("Błedne dane");
                    objectDataSend.setUserData(sendUserData);
                }else{
                    userData.setUsername(objectData.getUserData().getUsername());
                    sendUserData.setUsername(userData.getUsername());
                    sendUserData.setSessionToken(SessionGenerator.getSessionToken());
                    objectDataSend.setAuthenticated(true);
                    sendUserData.setUserID(myRs.getInt(1));
                    objectDataSend.setUserData(sendUserData);
                    UserRepository.getInstance().getUser(userData.getSessionNumber()).setUserID(sendUserData.getUserID());
                    UserRepository.getInstance().getUser(userData.getSessionNumber()).setUsername(userData.getUsername());


                    pstat.clearParameters();
                    pstat = myConn.prepareStatement("UPDATE users SET isActive = 1 WHERE userID = ?");
                    pstat.setInt(1,sendUserData.getUserID());
                    pstat.executeUpdate();



                }

            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }else{
            System.out.println("Dane do logowana sa puste");
        }

        return objectDataSend;
    }
}
