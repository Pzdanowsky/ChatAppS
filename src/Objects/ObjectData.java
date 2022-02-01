package Objects;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Objects.Chat;

public class ObjectData implements Serializable {

    public static final long serialVersionUID = 1L;
    private boolean authenticated;
    private String command; // Logowanie, Wysłanie wiadomosci, Wysyłanie pliku
    private String dataType; //MESSAGE, FILE
    private MessageObject messageObject;
    private FileObject fileObject;
    private UserData userData;
    private UserData userDataDestintion;
    private List<Chat> chatRoomList;

    public ObjectData() {
        chatRoomList = new ArrayList<>();
    }



    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public MessageObject getMessageObject() {
        return messageObject;
    }

    public void setMessageObject(MessageObject messageObject) {
        this.messageObject = messageObject;
    }

    public FileObject getFileObject() {
        return fileObject;
    }

    public void setFileObject(FileObject fileObject) {
        this.fileObject = fileObject;
    }


    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserData getUserDataDestintion() {
        return userDataDestintion;
    }

    public void setUserDataDestintion(UserData userDataDestintion) {
        this.userDataDestintion = userDataDestintion;
    }

    public void setChatRoomList(List<Chat> chatRoomList) {
        this.chatRoomList = chatRoomList;
    }

    public List<Chat> getChatRoomList(){
        return this.chatRoomList;
    }

    public void addChatToList(Chat chaRoom){
        this.chatRoomList.add(chaRoom);
    }



}
