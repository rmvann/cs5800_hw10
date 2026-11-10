package ChatApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private Message.MessageMemento lastMessage;
    private final String username; // usernames are unique
    private final HashMap<String, ChatHistory> chatHistories = new HashMap<>();
    private ChatServer chatServer;

    public User(String username, ChatServer chatServer){
        this.username = username;
        this.chatServer = chatServer;
    }

    public void sendMessage(String content, List<String> recipients){
        Message message = new Message(content, username, recipients);
        if (!chatServer.sendMessage(message)) return;

        lastMessage = message.snapshot();
        for (String recipient : recipients){
            if (!chatHistories.containsKey(recipient)) chatHistories.put(recipient, new ChatHistory());
            chatHistories.get(recipient).addMessage(message);
        }
    }

    public void receiveMessage(Message message){
        chatHistories.get(message.getSender()).addMessage(message);
    }

    public void undoMessage(){
        if(lastMessage == null){
            System.err.println("No message to undo");
            return;
        }

        lastMessage = null;
    }

    public void showHistoryWith(String otherUser){
        chatHistories.get(otherUser).showHistory();
    }

    public String getUsername() {
        return username;
    }
}
