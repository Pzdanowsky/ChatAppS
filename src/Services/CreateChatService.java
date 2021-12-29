package Services;

import Objects.Chat;
import Objects.ObjectData;
import Objects.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateChatService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;
    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {

        ObjectData objectDataSend = new ObjectData();
        UserData sendUserData = new UserData();
        UserData searchUser = new UserData();
        Chat chatRoom = new Chat();

        sendUserData.setSessionNumber(userData.getSessionNumber());
        objectDataSend.setUserData(sendUserData);
        objectDataSend.setCommand("01000");
        if (objectData == null) {
            System.out.println("Pusty obiekt");

        } else if (!objectData.getUserDataDestintion().getUsername().isEmpty()) {


            myConn = DatabaseConnectionService.getConn();

            try {
                PreparedStatement pstat = myConn.prepareStatement("INSERT INTO chat (chatID,chatName, createAt) VALUES (NULL,?, current_timestamp()); ");

                String chat_name = objectData.getUserData().getUsername()+"_"+objectData.getUserDataDestintion().getUsername();
                pstat.setString(1,chat_name );
                pstat.executeUpdate();

                pstat.clearParameters();
                pstat = myConn.prepareStatement("SELECT chatID FROM chat WHERE chatName =?");
                pstat.setString(1,chat_name);

                myRs = pstat.executeQuery();



                if(myRs.next()){
                    System.out.println("Otworzono pokoj czatu"+myRs.getInt(1));
                    int chatID = myRs.getInt(1);
                    objectDataSend.getUserData().setUserID(myRs.getInt(1));
                    objectDataSend.getUserData().setUsername(objectData.getUserData().getUsername());

                    chatRoom.setChatID(chatID);
                    pstat.clearParameters();
                    pstat = myConn.prepareStatement("SELECT userID FROM users WHERE login =?");
                    pstat.setString(1,objectData.getUserDataDestintion().getUsername());
                    int userId = 0;
                    myRs = pstat.executeQuery();
                    if(myRs.next()) {

                        userId = myRs.getInt(1);
                    }else{
                        System.out.println("nima");
                    }
                    pstat.clearParameters();
                    pstat = myConn.prepareStatement("INSERT INTO chat_user (userID,chatID) VALUES (?,?); ");
                    pstat.setInt(1,objectData.getUserData().getUserID());
                    pstat.setInt(2,chatID);
                    pstat.executeUpdate();
                    chatRoom.addUser(objectData.getUserData().getUserID());

                    pstat.clearParameters();
                    pstat = myConn.prepareStatement("INSERT INTO chat_user (userID,chatID) VALUES (?,?); ");
                    pstat.setInt(1, userId);
                    pstat.setInt(2,chatID);
                    pstat.executeUpdate();
                    chatRoom.addUser(userId);


                    objectDataSend.setChatRoom(chatRoom);

                }else{
                    objectDataSend.getUserData().setUserID(0);
                }


            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }
        return objectDataSend;
    }
}
