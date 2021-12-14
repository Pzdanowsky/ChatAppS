package Services;

import Objects.ObjectData;
import Objects.UserData;

import java.sql.Connection;
import java.sql.ResultSet;

public class MessageRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    private static UserData userData;

    private ObjectData objectData;

    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {
        ObjectData objectDataSend = new ObjectData();
      //  objectDataSend.setSessionNumber(userData.getSesionNumber());
        objectDataSend.setCommand("00001");

        if(objectData == null){
            System.out.println("Obiket wiadomosci jest pusty");
        }else{

        }




        return objectData;
    }
}
