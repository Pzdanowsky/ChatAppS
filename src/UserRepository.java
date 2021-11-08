import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static UserRepository instance = null;

    private Map<String, User> userList;

    public static synchronized UserRepository getInstance(){
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

    public User getUserSession(String sessionNumber){
        return  userList.get(sessionNumber);

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

    public int getOnlineList(){
        return userList.size();
    }
}
