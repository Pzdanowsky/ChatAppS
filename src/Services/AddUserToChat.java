package Services;

import Objects.ObjectData;
import Objects.UserData;
import Services.ReqStrategy.AddChatByOtherUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddUserToChat implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {

        UserData userDest = objectData.getUserDataDestintion();
        System.out.println("Mam uzytkownik: " + objectData.getUserDataDestintion().getUsername() + " do czatu: " + objectData.getChatRoomList().get(0).getChatID());

        if (objectData == null) {
            System.out.println("Pusty obiekt");

        } else if (!objectData.getUserDataDestintion().getUsername().isEmpty()) {


            myConn = DatabaseConnectionService.getConn();


            PreparedStatement pstat = null;
            try {
                pstat = myConn.prepareStatement("SELECT userID FROM users WHERE username = ?");
                pstat.setString(1,objectData.getUserDataDestintion().getUsername());
                myRs = pstat.executeQuery();


                if(myRs.next()) {
                    userDest.setUserID(myRs.getInt(1));

                    pstat.clearParameters();

                    pstat = myConn.prepareStatement("SELECT userID from chat_user WHERE chatID = ?",ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    pstat.setInt(1,objectData.getChatRoomList().get(0).getChatID());
                    myRs = pstat.executeQuery();

                    if(myRs.next()) {

                        myRs.last();
                        int ResultSize = myRs.getRow();
                        myRs.beforeFirst();
                        myRs.next();
                        for(int i = 0; i <= ResultSize; i++) {
                            if(userDest.getUserID() != myRs.getInt(1)) {
                                pstat = myConn.prepareStatement("INSERT INTO chat_user (userID,chatID) VALUES (?,?);");
                                pstat.setInt(1,userDest.getUserID());
                                pstat.setInt(2,objectData.getChatRoomList().get(0).getChatID());
                                RequestStrategy rs = new AddChatByOtherUser();
                                objectData.setUserData(userDest);
                                rs.processObjectData(userDest,objectData);
                                break;
                            }else {
                                System.out.println("Juz jest");
                            }

                        }


                    }


                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        return objectData;
        }
    }

