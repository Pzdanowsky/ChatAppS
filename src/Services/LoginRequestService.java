package Services;

import Objects.ObjectData;
import Objects.User;
import Repositories.DataReciveRepository;

import javax.imageio.IIOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    private static User user;

    private ObjectData objectData;


    @Override
    public ObjectData processObjectData(User user, ObjectData objectData) {

        ObjectData objectDataSend = new ObjectData();
        objectDataSend.setSessionNumber(user.getSesionNumber());
        objectDataSend.setCommand("00001");
        if(objectData == null){
            System.out.println("Pusty obiekt");
            objectDataSend.setAuthenticated(false);

        }else if(!objectData.getUsername().isEmpty() && !objectData.getPassword().isEmpty() ){



            myConn = DatabaseConnectionService.getConn();
            try {
                PreparedStatement pstat = myConn.prepareStatement("SELECT userID FROM users WHERE login =? AND password =?");
                pstat.setString(1,objectData.getUsername());
                pstat.setString(2,objectData.getPassword());

                myRs = pstat.executeQuery();

                if(!myRs.next()){
                    objectDataSend.setAuthenticated(false);
                    System.out.println("Błedne dane");
                }else{
                    user.setUsername(objectData.getUsername());

                    objectDataSend.setSesionToken(SessionGenerator.getSessionToken());
                    objectDataSend.setAuthenticated(true);
                    objectDataSend.setUserID(myRs.getInt(1));
                    objectDataSend.setUsername(user.getUsername());
                   // System.out.println("Zalogowano kurwa");
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
