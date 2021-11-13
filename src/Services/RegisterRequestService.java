package Services;

import Objects.ObjectData;
import Objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    private static User user;

    @Override
    public ObjectData processObjectData(User user, ObjectData objectData) {
        ObjectData objectDataSend = new ObjectData();

        objectDataSend.setSessionNumber(user.getSesionNumber());
        objectDataSend.setCommand("00010");
        if(objectData == null) {
            System.out.println("Pusty obiekt");
            objectDataSend.setAuthenticated(false);
        }else if(!objectData.getUsername().isEmpty() && !objectData.getPassword().isEmpty() && !objectData.getEmailUser().isEmpty() ){

            myConn = DatabaseConnectionService.getConn();

            try{
                PreparedStatement pstat = myConn.prepareStatement("SELECT userID FROM users WHERE login =?");
                pstat.setString(1,objectData.getUsername());

                myRs = pstat.executeQuery();

                if(myRs.next()) {
                    objectDataSend.setAuthenticated(false);
                    System.out.println("Użytkownik o takim nicku instieje");
                }else if(!myRs.next()){

                    pstat.clearParameters();
                    String query = "INSERT INTO users (login,password,email,name,surname) VALUE(?,?,?,?,?)";
                    pstat = myConn.prepareStatement(query);
                    pstat.setString(1,objectData.getUsername());
                    pstat.setString(2,objectData.getPassword());
                    pstat.setString(3,objectData.getEmailUser());
                    pstat.setString(4, objectData.getNameUser());
                    pstat.setString(5, objectData.getSurnameUser());

                    pstat.executeUpdate();

                    pstat.clearParameters();
                    pstat = myConn.prepareStatement("SELECT userID FROM users WHERE login =?");
                    pstat.setString(1,objectData.getUsername());

                    myRs = pstat.executeQuery();

                    if(myRs.next()){
                        System.out.println("Zarejestrowana");
                        objectDataSend.setUserID(myRs.getInt(1));
                        objectDataSend.setUsername(objectData.getUsername());


                    }else{
                        objectDataSend.setUserID(0);
                    }

                }

            }catch(SQLException ex){
                ex.printStackTrace();
            }


        }

            return objectDataSend;
    }
}
