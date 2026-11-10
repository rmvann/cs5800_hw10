package chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChatAppTest {
    ChatServer server;
    private User annie;
    private User robert;
    private User sheldon;

    @BeforeEach
    void setup() {
        server = new ChatServer();
        annie = server.registerUser("annie");
        robert = server.registerUser("robert");
        sheldon = server.registerUser("sheldon");
    }

    @Test
    public void registerUser() {
        User j = server.registerUser("jen2");
        assertNotNull(j);
        assertEquals("jen2", j.getUsername());
    }

    @Test
    void sendMessage() {
        annie.sendMessage("hello", List.of("robert"));

        assertTrue(robert.iterator(annie).hasNext());
        assertTrue(annie.iterator(robert).hasNext());
    }

    @Test
    void sendToMultipleRecipients() {
        annie.sendMessage("hello", List.of("robert", "sheldon"));

        assertTrue(robert.iterator(annie).hasNext());
        assertTrue(sheldon.iterator(annie).hasNext());
    }

    @Test
    void undoMessage() {
        annie.sendMessage("hello", List.of("robert"));
        annie.undoMessageWith("robert");

        assertFalse(annie.iterator(robert).hasNext());
    }

    @Test
    void blockUser() {
        annie.block("sheldon");

        sheldon.sendMessage("hello", List.of("annie"));

        assertFalse(annie.iterator(sheldon).hasNext());
    }

    @Test
    void iterateMessages() {
        annie.sendMessage("first", List.of("robert"));
        annie.sendMessage("second", List.of("robert"));

        Iterator<Message> iterator = annie.iterator(robert);

        assertEquals("first", iterator.next().getContent());
        assertEquals("second", iterator.next().getContent());
        assertFalse(iterator.hasNext());
    }
}
