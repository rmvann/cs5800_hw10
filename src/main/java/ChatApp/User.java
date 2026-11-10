package ChatApp;

import java.util.*;

public class User implements IterableByUser {
    private final Map<String, Message.MessageMemento> lastMessages = new HashMap<>();
    private final String username; // usernames are unique
    private final Map<String, ChatHistory> chatHistories = new HashMap<>();
    private ChatServer chatServer;

    public User(String username, ChatServer chatServer){
        this.username = username;
        this.chatServer = chatServer;
    }

    public void sendMessage(String content, List<String> recipients){
        Message message = new Message(content, username, recipients);
        if (!chatServer.sendMessage(message)) return;

        for (String recipient : recipients){
            lastMessages.put(recipient, message.snapshot());

            if (!chatHistories.containsKey(recipient)) chatHistories.put(recipient, new ChatHistory());
            chatHistories.get(recipient).addMessage(message);
        }
    }

    public void receiveMessage(Message message){
        if(!chatHistories.containsKey(message.getSender())) chatHistories.put(message.getSender(), new ChatHistory());
        chatHistories.get(message.getSender()).addMessage(message);
    }

    public void undoMessageWith(String recipient){
        if(!lastMessages.containsKey(recipient)){
            System.err.println("No message to undo");
            return;
        }

        chatHistories.get(recipient).undoLastMessage();
        System.out.println("Removed message: "+lastMessages.get(recipient));
        lastMessages.remove(recipient);

        chatServer.undoMessage(username, recipient);
    }

    public void undoReceivedMessage(String sender){
        chatHistories.get(sender).undoLastMessage();
        System.out.println("Received message deleted");
    }

    public void showHistoryWith(String otherUser){
        chatHistories.get(otherUser).showHistory();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        ChatHistory chatHistory = chatHistories.get(userToSearchWith.getUsername());

        if (chatHistory == null) return Collections.emptyIterator();

        return chatHistory.iterator(userToSearchWith);
    }
}
