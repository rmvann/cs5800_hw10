package ChatApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private Message.MessageMemento lastMessage;
    private final String username; // usernames are unique
    private HashMap<String, ChatHistory> chatHistories = new HashMap<>();

    public User(String username){
        this.username = username;
    }

    public void sendMessage(String content, List<String> recipients){
        Message message = new Message(content, username, recipients);
        lastMessage = message.snapshot();

    }

    public void receiveMessage(Message message){

    }

    public void undoMessage(){
        if(lastMessage == null){
            System.err.println("No message to undo");
            return;
        }

        lastMessage = null;
    }

    public String getUsername() {
        return username;
    }
}
