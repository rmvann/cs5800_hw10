package ChatApp;

import java.util.ArrayDeque;
import java.util.Deque;

public class ChatHistory {
    private Deque<Message> messageHistory = new ArrayDeque<>();

    public void addMessage(Message message){
        messageHistory.addLast(message);
    }

    public Message getHistory(){
        for (Message message: messageHistory){}
    }
}
