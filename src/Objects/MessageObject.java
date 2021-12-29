package Objects;

import java.io.Serializable;

public class MessageObject implements Serializable {

    private int id;
    private int idChatRoom;
    private String fromMessage;
    private String data;


    public MessageObject(){

    }

    public MessageObject(String from, String to, String data){
        this.fromMessage = from;

        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromMessage() {
        return fromMessage;
    }

    public void setFromMessage(String fromMessage) {
        this.fromMessage = fromMessage;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdChatRoom() {
        return idChatRoom;
    }

    public void setIdChatRoom(int idChatRoom) {
        this.idChatRoom = idChatRoom;
    }
}
