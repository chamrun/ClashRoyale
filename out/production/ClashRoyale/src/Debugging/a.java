package Debugging;

public class a {

    public static void a(String msg) {
        if (msg.contains("bot") || msg.contains("Bot")){
            System.out.println("\033[0;35m" + msg + "\033[0m");
        }
        else
            System.out.println(msg);
    }
}
