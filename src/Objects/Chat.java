package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Chat implements Serializable {

    private int chatID;
    private ArrayList<Integer> usersID;
    private HashMap<Integer,MessageObject> messageChatList;
    private HashMap<Integer, FileObject> fileChatList;
    int i;
    public Chat() {
        this.usersID = new ArrayList<>();
        this.messageChatList = new HashMap<>();
        this.fileChatList = new HashMap<>();
        i=0;
    }
    public void addMessage(MessageObject messageObject){
        i++;
        messageObject.setIdChatRoom(chatID);
        this.messageChatList.put(i, messageObject);
        System.out.println(chatID+ ": "+messageChatList.size());
    }

    public MessageObject getMessage(int messageID){
        return this.messageChatList.get(messageID);
    }

    public HashMap<Integer, MessageObject> getMessageChatList() {
        return messageChatList;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public void addUser(int userId){
        usersID.add(userId);
    }



}
