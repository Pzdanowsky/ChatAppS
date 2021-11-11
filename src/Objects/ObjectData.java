package Objects;

import java.io.File;
import java.io.Serializable;

public class ObjectData implements Serializable {

    public static final long serialVersionUID = 1L;
    private String sessionNumber; // Uni session number
    private String sesionToken ; // Session password
    private String username; // Login
    private String command; // Logowanie, Wysłanie wiadomosci, Wysyłanie pliku
    private String dataType; //MESSAGE, FILE
    private MessageObject messageObject;
    private FileObject fileObject;


    public ObjectData() {
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSesionToken() {
        return sesionToken;
    }

    public void setSesionToken(String sesionToken) {
        this.sesionToken = sesionToken;
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
}
