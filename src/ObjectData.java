import java.io.Serializable;

public class ObjectData implements Serializable {

    public static final long serialVersionUID = 1L;
    private String sessionNumber;
    private String username;
    private String command;
    private String dataType;
    private String data;


    public ObjectData() {
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setSUsername(String sesionPassword) {
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
}
