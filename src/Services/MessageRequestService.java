package Services;

import Objects.ObjectData;
import Objects.User;

import java.sql.Connection;
import java.sql.ResultSet;

public class MessageRequestService implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    private static User user;

    private ObjectData objectData;

    @Override
    public ObjectData processObjectData(User user, ObjectData objectData) {
        ObjectData objectDataSend = new ObjectData();
        objectDataSend.setSessionNumber(user.getSesionNumber());
        objectDataSend.setCommand("00001");

        if(objectData == null){
            System.out.println("Obiket wiadomosci jest pusty");
        }else{

        }




        return objectData;
    }
}
