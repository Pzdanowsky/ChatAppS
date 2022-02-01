package Services.ReqStrategy;

import Objects.ObjectData;
import Objects.UserData;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchUserRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;


    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {

        ObjectData objectDataSend = new ObjectData();
        UserData sendUserData = new UserData();
        UserData searchUser = new UserData();

        sendUserData.setSessionNumber(userData.getSessionNumber());
        objectDataSend.setUserData(sendUserData);
        objectDataSend.setCommand("00110");
        if (objectData == null) {
            System.out.println("Pusty obiekt");

        } else if (!objectData.getUserDataDestintion().getUsername().isEmpty()) {


            myConn = DatabaseConnectionService.getConn();

            try {
                PreparedStatement pstat = myConn.prepareStatement("SELECT userID, avatarID, name, surName FROM users WHERE login =?");
                pstat.setString(1, objectData.getUserDataDestintion().getUsername());

                myRs = pstat.executeQuery();


                if (!myRs.next()) {
                    System.out.println("Użytkownik o danym nicku nie istnieje");
                } else{
                    searchUser.setUserID(myRs.getInt(1));
                    searchUser.setAvatarID(myRs.getInt(2));
                    searchUser.setNameUser(myRs.getString(3));
                    searchUser.setSurname(myRs.getString(4));
                    System.out.println(searchUser.getSurname());
                    searchUser.setUsername(objectData.getUserDataDestintion().getUsername());
                    //System.out.println("Robimy to");
                    objectDataSend.setUserDataDestintion(searchUser);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }
        return objectDataSend;
    }


}
