package Objects;

import java.io.Serializable;

public class MessageObject implements Serializable {

    private int id;
    private int idChatRoom;
    private int authorId;
    private String content;


    public MessageObject(){

    }

    public MessageObject(int authorId, String content,int idChatRoom){
        this.authorId = authorId;
        this.content = content;
        this.idChatRoom = idChatRoom;
    }

    public MessageObject(int id, int idChatRoom, int authorId, String context){
        this.id = id;
        this.idChatRoom = idChatRoom;
        this.authorId = authorId;
        this.content = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdChatRoom() {
        return idChatRoom;
    }

    public void setIdChatRoom(int idChatRoom) {
        this.idChatRoom = idChatRoom;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String context) {
        this.content = context;
    }
}
