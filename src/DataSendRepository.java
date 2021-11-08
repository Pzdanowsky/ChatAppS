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
        sendList.put(objectDataSend.getDataType(), objectDataSend);
    }

    public ObjectData getObjectData(String dataType) {
        ObjectData obj = sendList.get(dataType);
        sendList.remove(dataType);
        return obj;
    }

}