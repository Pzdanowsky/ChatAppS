package Services;

import Objects.ObjectData;
import Objects.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CloseConnectionProgram implements RequestStrategy {

    private static ResultSet myRs;

    private static Connection myConn;

    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {



        if(objectData == null){
            System.out.println("Pusty obiekt");

        }else{
            myConn = DatabaseConnectionService.getConn();
            try {
                System.out.println("Zamykanie: "+ objectData.getUserData().getUserID());
                PreparedStatement pstat = myConn.prepareStatement("UPDATE users SET isActive = 0 WHERE userID = ?");
                pstat.setInt(1,objectData.getUserData().getUserID());
                pstat.executeUpdate();



            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

        return null;
    }
}
