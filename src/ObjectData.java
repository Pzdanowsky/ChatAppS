import java.io.Serializable;

public class ObjectData implements Serializable {

    public static final long serialVersionUID = 1L;
    private String sessionNumber; // Uni session number
    private String sesionToken ; // Session password
    private String username; // Login
    private String command; // Logowanie, Wysłanie wiadomosci, Wysyłanie pliku
    private String from; //FROM USER
    private String to; // TO USER OR TO SERWER
    private String dataType; //MESSAGE, FILE
    private String data; // DATA example: password, message text,


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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSesionToken() {
        return sesionToken;
    }

    public void setSesionToken(String sesionToken) {
        this.sesionToken = sesionToken;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
