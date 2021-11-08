import java.io.Serializable;

public class ObjectData implements Serializable {

    public static final long serialVersionUID = 1L;
    private int sessionNumber;
    private String sesionPassword;
    private String command;
    private String dataType;
    private String data;


    public ObjectData() {
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getSesionPassword() {
        return sesionPassword;
    }

    public void setSesionPassword(String sesionPassword) {
        this.sesionPassword = sesionPassword;
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
