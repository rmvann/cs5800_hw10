package ChatApp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Message {
    private String content;
    private String sender;
    private List<String> recipients;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public Message(String content, String sender, List<String> recipients) {
        this.content = content;
        this.sender = sender;
        this.recipients = recipients;
    }

    public MessageMemento snapshot(){
        return new MessageMemento(this);
    }


    public String formatTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }

    public static class MessageMemento{
        private String content;
        private String sender;
        private List<String> recipients;
        private final LocalDateTime timestamp;


        private MessageMemento(Message message) {
            this.content = message.content;
            this.sender = message.sender;
            this.recipients = message.recipients;
            this.timestamp = message.timestamp;
        }

        public String getContent() {
            return content;
        }
    }

    public List<String> getRecipients() {
        return recipients;
    }
    public String getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s -> %s: %s",
                formatTimestamp(),
                sender,
                String.join(", ", recipients),
                content
        );
    }
}
