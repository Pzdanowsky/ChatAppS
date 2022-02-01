package Services.ReqStrategy;

import Objects.ObjectData;
import Objects.UserData;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    private static UserData userData;

    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {
        ObjectData objectDataSend = new ObjectData();
        UserData userDataSend = new UserData();

        userDataSend.setSessionNumber(userData.getSessionNumber());

        objectDataSend.setCommand("00010");
        if(objectData == null) {
            System.out.println("Pusty obiekt");
            objectDataSend.setAuthenticated(false);
        }else if(!objectData.getUserData().getUsername().isEmpty() && !objectData.getUserData().getPassword().isEmpty() && !objectData.getUserData().getEmail().isEmpty() ){

            myConn = DatabaseConnectionService.getConn();

            try{
                PreparedStatement pstat = myConn.prepareStatement("SELECT userID FROM users WHERE login =?");
                pstat.setString(1,objectData.getUserData().getUsername());

                myRs = pstat.executeQuery();

                if(myRs.next()) {
                    objectDataSend.setAuthenticated(false);
                    System.out.println("Użytkownik o takim nicku instieje");
                }else if(!myRs.next()){

                    pstat.clearParameters();
                    String query = "INSERT INTO users (login,password,email,name,surname) VALUE(?,?,?,?,?)";
                    pstat = myConn.prepareStatement(query);
                    pstat.setString(1,objectData.getUserData().getUsername());
                    pstat.setString(2,objectData.getUserData().getPassword());
                    pstat.setString(3,objectData.getUserData().getEmail());
                    pstat.setString(4, objectData.getUserData().getNameUser());
                    pstat.setString(5, objectData.getUserData().getSurname());
                    pstat.executeUpdate();

                    pstat.clearParameters();
                    pstat = myConn.prepareStatement("SELECT userID FROM users WHERE login =?");
                    pstat.setString(1,objectData.getUserData().getUsername());

                    myRs = pstat.executeQuery();

                    if(myRs.next()){
                        System.out.println("Zarejestrowana");
                        objectDataSend.getUserData().setUserID(myRs.getInt(1));
                        objectDataSend.getUserData().setUsername(objectData.getUserData().getUsername());


                    }else{
                        objectDataSend.getUserData().setUserID(0);
                    }

                }

            }catch(SQLException ex){
                ex.printStackTrace();
            }


        }

            return objectDataSend;
    }
}
