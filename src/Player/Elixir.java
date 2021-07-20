package Player;

public class Elixir extends Thread{
    private int elixirs;

    @Override
    public void run() {
        elixirs = 8;

        while (true)//while (game is on)
        {
            try {
                Thread.sleep(2000);
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
}
