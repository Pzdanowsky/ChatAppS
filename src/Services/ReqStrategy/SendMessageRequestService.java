package Services.ReqStrategy;

import Objects.Chat;
import Objects.ObjectData;
import Objects.UserData;
import Repositories.DataSendRepository;
import Services.CommandManager;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;

import java.sql.*;

public class SendMessageRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static ResultSet myRs2;

    private static Connection myConn;

    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {
        ObjectData objectDataSend = new ObjectData();
        UserData sendUserData = new UserData();



        objectDataSend.setCommand("00111");
        if (objectData == null) {
            System.out.println("Pusty obiekt");

        } else if (!objectData.getMessageObject().equals(null)) {


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
                    pstat.clearParameters();
                    objectData.getMessageObject().setId(myRs.getInt(1));
                    pstat = myConn.prepareStatement("SELECT created FROM messages WHERE messageID = ?");
                    pstat.setInt(1,objectData.getMessageObject().getId());
                    myRs2 = pstat.executeQuery();
                    if(myRs2.next()) {
                        objectData.getMessageObject().setCreated(myRs2.getTimestamp(1));
                        objectDataSend.setMessageObject(objectData.getMessageObject());
                        objectDataSend.setUserData(objectData.getUserData());
                        objectDataSend.getUserData().setSessionNumber(userData.getSessionNumber());
                        RequestStrategy s = new MessageRequestService();
                        s.processObjectData(userData, objectData);
                    }

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }
        return objectDataSend;
    }
}