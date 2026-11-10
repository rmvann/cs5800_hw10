package chatapp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class ChatHistory implements IterableByUser {
    private final Deque<Message> messageHistory = new ArrayDeque<>();

    public void addMessage(Message message){
        messageHistory.addLast(message);
    }

    public void showHistory(){
        for (Message message: messageHistory){
            System.out.println(message);
        }
    }

    public void undoLastMessage(){
        messageHistory.removeLast();
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new SearchMessagesByUser(messageHistory.iterator(), userToSearchWith);
    }
}
