package ChatApp;

import java.util.ArrayDeque;
import java.util.Deque;

public class ChatHistory {
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
}
