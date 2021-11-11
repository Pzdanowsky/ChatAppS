import Objects.ObjectData;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static UserRepository instance = null;

    private Map<String, User> userList;

    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    private UserRepository(){
        userList = new HashMap<>();
    }

    public void addUser(String sessionNumber, User user){
        userList.put(sessionNumber,user);
    }

    public void deleteUser(String sessionNumber){
        userList.remove(sessionNumber);
    }

    public User getUser(String sesionNumber){
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

    public User searchUser(ObjectData object) {
        String username = object.getMessageObject().getToMessage();
        if (!userList.isEmpty()) {
            for (Map.Entry<String, User> entry : userList.entrySet()) {
                User userIter = entry.getValue();
                if (userIter.getUsername().equals(username)) {
                    //System.out.println(userIter.getUsername()+ "serachUser");
                    return userIter;

                }
            }
        }
        return null;
    }

    public void updateUser (User user) {
        User u1 = userList.get(user.getSesionNumber());
        System.out.println(u1.getUsername());
    }


    public int getOnlineList(){
        System.out.println(userList.size());
        return userList.size();
    }
}
