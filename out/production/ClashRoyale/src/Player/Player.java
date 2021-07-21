package Player;

public abstract class Player extends Thread{

    protected String[] deck;
    protected String[] readyCards;





    @Override
    public void run() {
        elixir = new Elixir();
        elixir.run();
        play();
    }

    protected Elixir elixir;
    public int getElixir() {
        return elixir.getElixirs();
    }



    protected abstract void play();


    public void putACard() {

    }




}
