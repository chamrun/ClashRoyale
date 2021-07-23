package Player;

import Debugging.a;

import java.util.Timer;
import java.util.TimerTask;

public class Elixir extends Thread{

    private int elixirs;
    boolean gameIsOn;
    long millisPerElixir;

    public Elixir(){
        a.a("Setting elixir...");
        elixirs = 4;
        gameIsOn = true;
        millisPerElixir = 2000;

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                millisPerElixir = 1000;
            }
        }, 120000);

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                gameIsOn = false;
            }
        }, 180000);

        a.a("elixir constructed...");

        start();

    }

    @Override
    public void run() {


        while (gameIsOn)
        {
            try {
                Thread.sleep(millisPerElixir);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (elixirs < 10)
                elixirs++;


        }
    }


    public int getElixirs() {
        return elixirs;
    }

    public boolean use(int cost) {
        if (cost < elixirs) {
            elixirs -= cost;
            return true;
        }
        return false;
    }
}
