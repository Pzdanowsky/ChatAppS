import java.util.HashMap;
import java.util.Map;

public class DataSendRepository {

    private static DataSendRepository instance;
    private static Map<String, ObjectData> sendList;


    public static DataSendRepository getInstance() {
        if (instance == null) {
            instance = new DataSendRepository();
        }

        return instance;
    }

    private DataSendRepository() {
        sendList = new HashMap<>();
    }

    public void addDataSend(ObjectData objectDataSend) {
        sendList.put(objectDataSend.getSessionNumber(), objectDataSend);
    }

    public ObjectData getObjectData(String sessionNumber) {
        ObjectData obj = sendList.get(sessionNumber);
        sendList.remove(sessionNumber);
        return obj;
    }

}