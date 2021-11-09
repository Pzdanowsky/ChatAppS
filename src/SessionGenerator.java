import java.util.Random;

public class SessionGenerator {

    private static Random ran;

    private static final String temp = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public static String getSesionNumber() {
        ran = new Random();
        int x = ran.nextInt(9001) + 999;
        String sessionNumber = Integer.toString(x);
        return sessionNumber;
    }

    public static String getSessionToken(){
        ran = new Random();
        StringBuilder pass = new StringBuilder(10);
        for(int i = 0; i < 15;i++) {
            int randInt = ran.nextInt(temp.length());
            char randChar = temp.charAt(randInt);
            pass.append(randChar);
        }
        return pass.toString();
    }

    public static String getChatRoomNumber(){
        ran = new Random();
        int x = ran.nextInt(99) + 10;
        String roomNumber = Integer.toString(x);
        return roomNumber;
    }
}