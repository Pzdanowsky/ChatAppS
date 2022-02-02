package Services.ReqStrategy;

import Objects.ObjectData;
import Objects.UserData;
import Repositories.DataSendRepository;
import Repositories.UserRepository;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;

import java.sql.*;

public class MessageRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static ResultSet myRs2;

    private static Connection myConn;

    private static UserData userData;

    private ObjectData objectData;

    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData){
        ObjectData objectDataSend = new ObjectData();
        objectDataSend.setCommand("01010");

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
                   myRs.last();
                   int RZ = myRs.getRow();
                    myRs.beforeFirst();
                    myRs.next();
                    for(int i = 1; i <= RZ;i++){
                    objectDataSend.setMessageObject(objectData.getMessageObject());
                    pstat.clearParameters();
                    pstat = myConn.prepareStatement("SELECT userID FROM users WHERE userID = ? AND isActive = 1" , ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    pstat.setInt(1, myRs.getInt(1));
                    myRs2 = pstat.executeQuery();


                   if(!myRs2.next()){
                       System.out.println("Brak");
                   }else if(UserRepository.getInstance().searchEqualsId(myRs2.getInt(1)) == null) {
                       System.out.println("Nie ma uzytkownika "+ myRs2.getInt(1));
                       ;
                   }else {


                           if(objectData.getMessageObject().getAuthorId() == myRs2.getInt(1)) {
                               System.out.println("Nie wyślesz sam do siebie");
                           }else{
                               objectDataSend.setUserData(UserRepository.getInstance().searchEqualsId(myRs.getInt(1)));
                               objectDataSend.setMessageObject(objectData.getMessageObject());
                               DataSendRepository.getInstance().addDataSend(objectDataSend);
                               System.out.println("Poszło ");
                            }
                   }
                    myRs.next();
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
