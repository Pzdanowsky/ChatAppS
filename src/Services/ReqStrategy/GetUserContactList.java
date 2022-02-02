package Services.ReqStrategy;

import Objects.Chat;
import Objects.ObjectData;
import Objects.UserData;
import Services.DatabaseConnectionService;
import Services.RequestStrategy;

import java.sql.*;

public class GetUserContactList implements RequestStrategy {
    private static ResultSet myRs;

    private static Connection myConn;

    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {

        ObjectData objectDataSend = new ObjectData();
        UserData sendUserData = new UserData();
        objectDataSend.setUserData(objectData.getUserData());
        objectDataSend.setCommand("00101");
        if (objectData == null) {
            System.out.println("Pusty obiekt");

        } else {


            myConn = DatabaseConnectionService.getConn();

            try {
                PreparedStatement pstat = myConn.prepareStatement("SELECT chatID FROM chat_user WHERE userID = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                pstat.setInt(1,objectData.getUserData().getUserID() );

                myRs = pstat.executeQuery();


                if(!myRs.next()){
                    System.out.println("Brak czatów dla ID: "+objectData.getUserData().getUserID());


                }else{
                    System.out.println("Znaleziono czaty dla ID "+objectData.getUserData().getUserID());
                    ResultSetMetaData rms = myRs.getMetaData();
                    myRs.last();
                    int ResultSize = myRs.getRow();
                    myRs.beforeFirst();
                    myRs.next();
                    for(int i = 1; i <= ResultSize; i++){
                        objectDataSend.addChatToList(new Chat(myRs.getInt(1)));
                        myRs.next();
                    }

                }


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

    }

        return objectDataSend;
}
}

