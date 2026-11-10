package ChatApp;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SearchMessagesByUser implements Iterator<Message> {
    private final Iterator<Message> messages;
    private final String username;

    private Message nextMessage;
    private boolean ready = false;

    public SearchMessagesByUser(Iterator<Message> messages, User user) {
        this.messages = messages;
        this.username = user.getUsername();
    }

    @Override
    public boolean hasNext() {

        if (ready) {
            return true;
        }

        while (messages.hasNext()) {
            Message message = messages.next();

            if (message.getSender().equals(username) || message.getRecipients().contains(username)) {

                nextMessage = message;
                ready = true;
                return true;
            }
        }

        return false;
    }

    @Override
    public Message next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        ready = false;
        return nextMessage;
    }
}
