package Services.ReqStrategy;

import Objects.Chat;
import Objects.ObjectData;
import Objects.UserData;
import Repositories.DataSendRepository;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;

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




                PreparedStatement pstat = myConn.prepareStatement("SELECT chatID FROM chat WHERE chatName = ?");
                String chat_name = objectData.getUserData().getUsername()+"_"+objectData.getUserDataDestintion().getUsername();
                pstat.setString(1,chat_name);
                myRs = pstat.executeQuery();



                if(!myRs.next()) {

                    pstat = myConn.prepareStatement("SELECT chatID FROM chat WHERE chatName = ?");
                    String chat_name2 = objectData.getUserDataDestintion().getUsername() + "_" + objectData.getUserData().getUsername();
                    pstat.setString(1, chat_name2);
                    myRs = pstat.executeQuery();

                    if (!myRs.next()) {

                        pstat = myConn.prepareStatement("INSERT INTO chat (chatID,chatName, createAt) VALUES (NULL,?, current_timestamp()); ");

                        pstat.setString(1, chat_name);
                        pstat.executeUpdate();

                        pstat.clearParameters();
                        pstat = myConn.prepareStatement("SELECT chatID FROM chat WHERE chatName =?");
                        pstat.setString(1, chat_name);

                        myRs = pstat.executeQuery();


                        if (myRs.next()) {
                            System.out.println("Otworzono pokoj czatu" + myRs.getInt(1));
                            int chatID = myRs.getInt(1);
                            objectDataSend.getUserData().setUserID(myRs.getInt(1));
                            objectDataSend.getUserData().setUsername(objectData.getUserData().getUsername());

                            chatRoom.setChatID(chatID);
                            pstat.clearParameters();
                            pstat = myConn.prepareStatement("SELECT userID FROM users WHERE login =?");
                            pstat.setString(1, objectData.getUserDataDestintion().getUsername());
                            int userId = 0;
                            myRs = pstat.executeQuery();
                            if (myRs.next()) {

                                userId = myRs.getInt(1);
                               searchUser.setUserID(userId);
                               searchUser.setUsername(objectData.getUserDataDestintion().getUsername());
                            } else {
                                System.out.println("nima");
                            }
                            pstat.clearParameters();
                            pstat = myConn.prepareStatement("INSERT INTO chat_user (userID,chatID) VALUES (?,?); ");
                            pstat.setInt(1, objectData.getUserData().getUserID());
                            pstat.setInt(2, chatID);
                            pstat.executeUpdate();
                            chatRoom.addUser(objectData.getUserData().getUserID());

                            pstat.clearParameters();
                            pstat = myConn.prepareStatement("INSERT INTO chat_user (userID,chatID) VALUES (?,?); ");
                            pstat.setInt(1, userId);
                            pstat.setInt(2, chatID);
                            pstat.executeUpdate();
                            chatRoom.addUser(userId);



                            objectDataSend.setUserDataDestintion(searchUser);
                            objectDataSend.addChatToList(chatRoom);
                            objectData = objectDataSend;
                            RequestStrategy rs = new AddChatByOtherUser();
                            DataSendRepository.getInstance().addDataSend(rs.processObjectData(userData,objectData));


                        } else {
                            objectDataSend.getUserData().setUserID(0);
                            System.out.println("Pomylka");
                        }
                    } else {
                        System.out.println("Jest juz taki pokoj czatu");
                    }
                }else{
                    System.out.println("Jest juz taki pokoj czatu");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }

        return objectDataSend;
    }
}
