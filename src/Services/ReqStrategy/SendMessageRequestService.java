package Services.ReqStrategy;

import Objects.Chat;
import Objects.ObjectData;
import Objects.UserData;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;

import java.sql.*;

public class SendMessageRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {
        ObjectData objectDataSend = new ObjectData();
        UserData sendUserData = new UserData();


        objectDataSend.setCommand("00111");
        if (objectData == null) {
            System.out.println("Pusty obiekt");

        } else if (!objectData.getMessageObject().equals(null)) {
            System.out.println("Wiadomość");

            myConn = DatabaseConnectionService.getConn();

            try {
                PreparedStatement pstat = myConn.prepareStatement("INSERT INTO messages (messageID,chatID,authorID, content,dataType,created) VALUES (NULL,?,?,?,?, current_timestamp()); ", Statement.RETURN_GENERATED_KEYS);


                pstat.setInt(1, objectData.getMessageObject().getIdChatRoom());
                pstat.setInt(2, objectData.getUserData().getUserID());
                pstat.setString(3, objectData.getMessageObject().getContent());
                pstat.setString(4, "text");

                pstat.executeUpdate();
                myRs = pstat.getGeneratedKeys();
                if(myRs.next()){
                    objectData.getMessageObject().setId(myRs.getInt(1));
                    objectDataSend.setMessageObject(objectData.getMessageObject());
                    objectDataSend.setUserData(objectData.getUserData());
                }
                pstat.clearParameters();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }
        return objectDataSend;
    }
}