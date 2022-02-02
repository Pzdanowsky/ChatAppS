package Objects;


import Services.Observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Chat implements Serializable, Observable {

    private int chatID;
    private ArrayList<Integer> usersID;
    private ArrayList<MessageObject> messageChatList;
    private HashMap<Integer, FileObject> fileChatList;
    int i;


    public Chat(int chatID) {
        this.chatID = chatID;
        this.usersID = new ArrayList<>();
        this.messageChatList = new ArrayList<>();
        this.fileChatList = new HashMap<>();
        i=0;
    }

    public Chat() {
        this.usersID = new ArrayList<>();
        this.messageChatList = new ArrayList<>();
        this.fileChatList = new HashMap<>();
        i=0;
    }

    public void addMessage(MessageObject messageObject){
        messageObject.setIdChatRoom(chatID);
        this.messageChatList.add(messageObject);
        notifyObserver();
    }

    public void addMessageList(ArrayList<MessageObject> messageChatList){
        for (MessageObject msg : messageChatList)
        {
            this.messageChatList.add(msg);
        }
    }

    public MessageObject getMessage(int messageID){
        return this.messageChatList.get(messageID);
    }

    public ArrayList<MessageObject> getMessageChatList() {
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

    @Override
    public void notifyObserver() {
       // ChatBoxManager.getInstance().updateNotify();
    }

}
