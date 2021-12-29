package Repositories;

import Objects.ObjectData;
import Objects.UserData;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static UserRepository instance = null;

    private Map<String, UserData> userList;

    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    private UserRepository(){
        userList = new HashMap<>();
    }

    public void addUser(String sessionNumber, UserData userData){
        userList.put(sessionNumber, userData);
    }

    public void deleteUser(String sessionNumber){
        userList.remove(sessionNumber);
    }

    public UserData getUser(String sesionNumber){
        return  userList.get(sesionNumber);

    }

    public void getStatusUser(String sessionNumber){

    }

    public void printUserList(){
        userList.keySet().forEach(System.out::println);
    }

    public boolean searchEquals(String sessionNumber){
        boolean test = userList.containsKey(sessionNumber);
        if(test){
            return true;
        }else{
            return false;
        }
    }
/*
    public UserData searchUser(ObjectData object) {
        String username = object.getMessageObject().getToMessage();
        if (!userList.isEmpty()) {
            for (Map.Entry<String, UserData> entry : userList.entrySet()) {
                UserData userDataIter = entry.getValue();
                if (userDataIter.getUsername().equals(username)) {
                    //System.out.println(userIter.getUsername()+ "serachUser");
                    return userDataIter;

                }
            }
        }
        return null;
    }
*/
    public void updateUser (UserData userData) {
        UserData u1 = userList.get(userData.getSessionNumber());
        System.out.println(u1.getUsername());
    }


    public int getOnlineList(){
        System.out.println(userList.size());
        return userList.size();
    }
}
