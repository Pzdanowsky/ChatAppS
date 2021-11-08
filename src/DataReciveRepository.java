import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataReciveRepository {

    private static DataReciveRepository instance;
    private static Map<String,ObjectData> reciveList;



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
        reciveList.put(objectDataRecive.getDataType(),objectDataRecive);
   }

   public ObjectData getObjectData(String dataType){
        ObjectData obj = reciveList.get(dataType);
        reciveList.remove(dataType);
        return obj;
   }

   public int getSizMap(){
        return reciveList.size();
   }



}
