package Player;

public abstract class Player extends Thread{

    protected String[] deck;
    protected String[] readyCards;




    protected abstract void play();


    public void putACard(){

    }


    //deck = new String[8];
    //readyCards = new String[4];








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




}
