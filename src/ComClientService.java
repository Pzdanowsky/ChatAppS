import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ComClientService extends Thread{

    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    private String sessionNumber;

    ComClientService(ObjectInputStream objectIn, ObjectOutputStream objectOut, String sessionNumber){
        this.objectIn = objectIn;
        this.objectOut = objectOut;
    }


        @Override
        public void run(){
            CommunicationServices.recive(objectIn,UserRepository.getInstance().getUser(sessionNumber));
            CommunicationServices.send(objectOut,UserRepository.getInstance().getUser(sessionNumber));
        }

}



