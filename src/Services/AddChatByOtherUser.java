package Services;

import Objects.ObjectData;
import Objects.UserData;
import Repositories.DataSendRepository;
import Repositories.UserRepository;

public class AddChatByOtherUser implements RequestStrategy {
    @Override
    public ObjectData processObjectData(UserData userData, ObjectData objectData) {
        objectData.setCommand("01011");
        if(UserRepository.getInstance().searchEqualsId(objectData.getUserDataDestintion().getUserID()) == null){
            System.out.println("Ten uzytkownik nie jest aktywny");
        }else{
            objectData.setUserData(objectData.getUserDataDestintion());
            objectData.getUserData().setSessionNumber(UserRepository
                    .getInstance().searchEqualsId(objectData.getUserDataDestintion()
                            .getUserID()).getSessionNumber());


            DataSendRepository.getInstance().addDataSend(objectData);


        }


        return objectData;
    }
}
