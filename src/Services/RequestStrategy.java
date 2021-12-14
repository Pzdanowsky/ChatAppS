package Services;


import Objects.ObjectData;
import Objects.UserData;

public interface RequestStrategy {

    public ObjectData processObjectData(UserData userData, ObjectData objectData);
}
