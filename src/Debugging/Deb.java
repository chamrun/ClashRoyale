package Debugging;

public class Deb {

    private static boolean deb = false;

    public static void print(String msg) {
        if (deb)
            System.out.println(msg);
    }
}
