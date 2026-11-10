package ChatApp;

import java.util.List;

public class ChatAppDriver {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        User annie = server.registerUser("annie");
        User robert = server.registerUser("robert22");
        User sheldon = server.registerUser("xX_Darkness_Xx");

        annie.sendMessage("hello!", List.of("robert22"));
        robert.showHistoryWith("annie");
        annie.showHistoryWith("robert22");

        System.out.println("---------");

        robert.sendMessage("hey", List.of("annie"));
        annie.showHistoryWith("robert22");

        System.out.println("---------");

        robert.undoMessageWith("annie");
        annie.showHistoryWith("robert22");
        robert.showHistoryWith("annie");

        System.out.println("---------");

        robert.undoMessageWith("annie");
        annie.showHistoryWith("robert22");

        System.out.println("---------");

        sheldon.sendMessage("yo!!!!", List.of("annie", "robert22"));
        robert.sendMessage("whats up", List.of("annie", "xX_Darkness_Xx"));
        annie.sendMessage("hi", List.of("xX_Darkness_Xx", "robert22"));

        System.out.println("sheldon and annie");
        sheldon.showHistoryWith("annie");

        System.out.println("sheldon and robert");
        sheldon.showHistoryWith("robert22");

        System.out.println("annie and robert");
        annie.showHistoryWith("robert22");
    }
}
