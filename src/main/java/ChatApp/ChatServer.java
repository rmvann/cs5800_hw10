package ChatApp;

import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    private Map<String, User> users = new HashMap<>();

    public void registerUser(String username){
        if(users.containsKey(username)){
            System.err.println("Username already exists");
            return;
        }
        else{
            users.put(username, new User(username));
            System.out.println(username + " successfully registered");
        }
    }

    public void unregisterUser(String username){
        if(!users.containsKey(username)){
            System.err.println("User not found");
        }
        else{
            users.remove(username);
            System.out.println(username + " successfully unregistered");
        }
    }

    public void sendMessage(String message){

    }
}
