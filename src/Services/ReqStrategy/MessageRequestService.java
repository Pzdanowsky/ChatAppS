package Services.ReqStrategy;

import Objects.ObjectData;
import Objects.UserData;
import Repositories.UserRepository;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;

import java.sql.*;

public class MessageRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    private static UserData userData;

    private ObjectData objectData;

    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData){
        ObjectData objectDataSend = new ObjectData();
        objectDataSend.setCommand("00001");
        System.out.println("Poszło ");

        if(objectData == null){
            System.out.println("Obiket wiadomosci jest pusty");
        }else{

            myConn = DatabaseConnectionService.getConn();
            try {
                PreparedStatement pstat = myConn.prepareStatement("SELECT userID FROM chat_user WHERE chatID = ?" , ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                pstat.setInt(1, objectData.getMessageObject().getIdChatRoom());
                pstat.executeQuery();

                myRs = pstat.executeQuery();

                if(myRs.next()){
                    objectDataSend.setMessageObject(objectData.getMessageObject());
                    pstat.clearParameters();
                    pstat = myConn.prepareStatement("SELECT userID FROM users WHERE userID = ? AND isActive = 1" , ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    pstat.setInt(1, myRs.getInt(1));
                    myRs = null;
                    myRs = pstat.executeQuery();
                    myRs.beforeFirst();
                    myRs.next();
                    if(UserRepository.getInstance().searchEqualsId(myRs.getInt(1)) == null){
                        System.out.println("Brak aktywnych ziomówWiadomość");
                    }else{
                        System.out.println("Poszło ");
                        myRs.beforeFirst();
                        myRs.next();
                        objectDataSend.setUserData(UserRepository.getInstance().searchEqualsId(myRs.getInt(1)));
                        objectDataSend.setMessageObject(objectData.getMessageObject());
                    }

                }
                pstat.clearParameters();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }




        return objectData;
    }
}
