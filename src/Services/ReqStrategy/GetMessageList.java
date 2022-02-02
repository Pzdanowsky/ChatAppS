package Services.ReqStrategy;

import Objects.Chat;
import Objects.MessageObject;
import Objects.ObjectData;
import Objects.UserData;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;

import java.sql.*;

public class GetMessageList implements RequestStrategy {
    private static ResultSet myRs;

    private static Connection myConn;
    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {
        ObjectData objectDataSend = new ObjectData();
        objectDataSend.setUserData(objectData.getUserData());
        objectDataSend.setCommand("01001");
        if (objectData == null) {
            System.out.println("Pusty obiekt");

        } else {

            myConn = DatabaseConnectionService.getConn();

            try {
                PreparedStatement pstat = myConn.prepareStatement("SELECT messageID, chatID, authorID, content, created FROM messages WHERE chatID = ? ORDER BY created DESC LIMIT 10", ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                pstat.setInt(1,objectData.getChatRoomList().get(0).getChatID() );

                myRs = pstat.executeQuery();


                if(!myRs.next()){
                    System.out.println("Brak wiadomości dla ID: "+objectData.getUserData().getUserID());


                }else{
                    ResultSetMetaData rms = myRs.getMetaData();
                    myRs.last();
                    int ResultSize = myRs.getRow();
                    myRs.beforeFirst();
                    myRs.next();
                    Chat chat = new Chat();
                    chat.setChatID(objectData.getChatRoomList().get(0).getChatID());
                    MessageObject msg;
                    for(int i = 1; i <= ResultSize; i++){
                        msg = new MessageObject();
                        msg.setId(myRs.getInt(1));
                        msg.setIdChatRoom(myRs.getInt(2));
                        msg.setAuthorId(myRs.getInt(3));
                        msg.setContent(myRs.getString(4));
                        msg.setCreated(myRs.getTimestamp(5));
                        chat.addMessage(msg);
                        myRs.next();
                    }
                    int sizetab = chat.getMessageChatList().size();

                    objectDataSend.addChatToList(chat);
                }


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return objectDataSend;

    }
}
