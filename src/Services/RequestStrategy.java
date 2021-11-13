package Services;


import Objects.ObjectData;
import Objects.User;

public interface RequestStrategy {



    public ObjectData processObjectData(User user, ObjectData objectData);
}
