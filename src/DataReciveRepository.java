import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataReciveRepository {

    private static DataReciveRepository instance;
    private static Map<String,ObjectData> reciveList; //Map<objectDataRecive.getTo(), ObjectData>



    public static DataReciveRepository getInstance(){
        if(instance == null){
            instance = new DataReciveRepository();
        }

        return instance;
    }

   private DataReciveRepository(){
        reciveList = new HashMap<>();
   }

   public void addDataRecive(ObjectData objectDataRecive){
        reciveList.put(objectDataRecive.getSessionNumber(),objectDataRecive);
   }

   public ObjectData getObjectData(String sessionNumber){
        ObjectData obj = reciveList.get(sessionNumber);
        reciveList.remove(sessionNumber);
        return obj;
   }

   public int getSizMap(){
        return reciveList.size();
   }



}
