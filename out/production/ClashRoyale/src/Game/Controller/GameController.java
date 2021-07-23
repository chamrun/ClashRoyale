package Game.Controller;

import Audio.Audio;
import Debugging.Deb;
import Debugging.a;
import Game.Model.*;
import Game.Model.Buildings.Cannon;
import Game.Model.Buildings.InfernoTower;
import Game.Model.Soldiers.*;
import Game.Model.Spells.Arrows;
import Game.Model.Spells.Fireball;
import Game.Model.Spells.Rage;
import Game.Model.Towers.King;
import Game.Model.Towers.Queen;
import Game.View.GameView;
import Player.Bot;
import Player.User;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class GameController {
    private String[] deck;
    private Location[][] locations;
    private HashMap<Image, String> cardImages = new HashMap<>();
    private Image chosenCard = null;
    private boolean isGameOver = false;
    private int gameTime = 3 * 60 ;
    private boolean doubleElixir = false;
    private long elixirTime = 2 * 1000;
    private ImageView[] cardImageViews = new ImageView[5];
    private ImageView chosenImageView;
    private Location chosenLocation;
    private Board board;
    private LinkedList<String> card;
    private int elixir = 4;

    private User user;
    private Bot bot;
    private Team loser;

    public GameView getGameView() {
        return gameView;
    }

    public void setDeck(String[] deck) {
        this.deck = deck;
    }

    @FXML
    private ProgressBar elixirProgressBar;


    @FXML
    private Text elixirText;

    @FXML
    private Text timeText;


    public void setBoard(Board board) {



    }

    private GameView gameView;


    public GameController() {
        this.gameView = new GameView(this);
    }

    public void initialize() {

    }

    public void startElixirTimer() {
        Service service = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        while (!isGameOver) {
                            if (elixir == 10)
                                continue;
                            elixir++;
                            updateProgress(elixir, 10);
                            Deb.print("Elixir : " + elixir);
                            long time = (doubleElixir) ? elixirTime / 2 : elixirTime;
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }
                };
            }
        };
        elixirProgressBar.progressProperty().bind(service.progressProperty());
        service.start();

    }


    public void startTimeTimer() {

        Service service = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        for (int i = gameTime; i > 0; i--) {
                            updateProgress(i, 180);
                            if (isGameOver)
                                return null;
                            if (i == gameTime / 3)
                                doubleElixir = true;

                            if (i % 50 == 0)
                                System.out.println("time left: " + i);


                            try {
                                Thread.sleep(1 * 100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        isGameOver = true;
                        return null;
                    }
                };
            }
        };
        timeProgressBar.progressProperty().bind(service.progressProperty());
        service.start();
    }

    public void createCardImages() {


        //System.out.println("Creating image cards...   :" + card);
        card = new LinkedList<>();
        //System.out.println("LinkedList was made...");
        //System.out.println("card: " + card + "\n" +
          //      "cards: " + Arrays.toString(deck));



        for (String cardName : deck) {
            switch (cardName) {
                case "Archers" ->       cardImages.put(new Image("Cards/Archers.png"), cardName);
                case "BabyDragon" ->    cardImages.put(new Image("Cards/BabyDragon.png"), cardName);
                case "Arrows" ->        cardImages.put(new Image("Cards/Arrows.png"), cardName);
                case "Barbarian" ->     cardImages.put(new Image("Cards/Barbarian.png"), cardName);
                case "Cannon" ->        cardImages.put(new Image("Cards/Cannon.png"), cardName);
                case "Fireball" ->      cardImages.put(new Image("Cards/Fireball.png"), cardName);
                case "Giant" ->         cardImages.put(new Image("Cards/Giant.png"), cardName);
                case "InfernoTower" ->  cardImages.put(new Image("Cards/InfernoTower.png"), cardName);
                case "MiniPEKKA" ->     cardImages.put(new Image("Cards/MiniPEKKA.png"), cardName);
                case "Rage" ->          cardImages.put(new Image("Cards/Rage.png"), cardName);
                case "Valkyrie" ->      cardImages.put(new Image("Cards/Valkyrie.png"), cardName);
                case "Wizard" ->        cardImages.put(new Image("Cards/Wizard.png"), cardName);
                default -> System.out.println(cardName + "is not valid!\n");
            }
            card.add(cardName);
        }
        Deb.print(cardImages.size() + " cards have created.");
        initializeCardImageViews();
    }

    public void initializeCardImageViews() {
        cardImageViews[0] = cardImageView1;
        cardImageViews[1] = cardImageView2;
        cardImageViews[2] = cardImageView3;
        cardImageViews[3] = cardImageView4;
        cardImageViews[4] = cardImageView5;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    cardImageViews[i].setImage((Image) cardImages.keySet().toArray()[i]);
                }
            }
        });
//            System.out.println(cardImageViews[i].getFitHeight() +" | "+cardImageViews[i].getFitWidth());
        Deb.print("5 cards added to the screen.");
    }

    public void initializeCardImageViews(LinkedList<String> card) {
        cardImageViews = new ImageView[5];
        cardImageViews[0] = cardImageView1;
        cardImageViews[1] = cardImageView2;
        cardImageViews[2] = cardImageView3;
        cardImageViews[3] = cardImageView4;
        cardImageViews[4] = cardImageView5;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    cardImageViews[i].setImage(getKey(cardImages, card.get(i)));
                }
            }
        });
//            System.out.println(cardImageViews[i].getFitHeight() +" | "+cardImageViews[i].getFitWidth());
        Deb.print("5 cards added to the screen.");
    }

    public Image getKey(HashMap<Image, String> cardImage, String string) {
        for (Map.Entry<Image, String> entry : cardImage.entrySet()) {
            if (entry.getValue().equals(string))
                return entry.getKey();
        }
        return null;
    }

    public void setProgress(ProgressBar progressBar,double i){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(i);
            }
        });
    }


    public void removeElement(Node node) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                landPane.getChildren().remove(node);
            }
        });
        Deb.print("node deleted.");
    }

    @FXML
    private ProgressBar timeProgressBar;

    @FXML
    public Pane landPane;

    @FXML
    public ImageView backgroundImage;


    public void addPlayersElixirs() {
        //add Elixirs to players
    }


//    @FXML
//    void clickMouseOnLandPane(MouseEvent event) {
//        if (count == 0){
//            initializeCardImageViews();
//            board = new Board(18,35,18);
//            locations = board.getLocations();
//        }
//        count++;
//
//
//        Deb.print("Mouse clicked on (source : " + event.getSource() + " ): X = " + event.getX() + "  Y = " + event.getY());
//        chosenLocation = locations[(int) (event.getX() / gameView.getTileWidth())][(int) (event.getY() / gameView.getTileHeight())];
////        chosenLocation = locations[(int) (event.getY() / gameView.getTileHeight())][(int) (event.getX() / gameView.getTileWidth())];
//            if (chosenLocation.isEmpty()) {
//                Card card = new Barbarian(board, Level.ONE,chosenLocation,(count%2 == 1)? Team.A:Team.B,this);
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        landPane.getChildren().add(((Fightable)card).getCurrentImage());
//                    }
//                });
//                Deb.print("Class : GameController | method : clickMouseOnLandPane " +
//                        "| new card added to landPane: Location : X = "+chosenLocation.getX()+" Y = "+chosenLocation.getY());
//        }
//
////        resetCardAndLocation();
//    }

    public void addElement(Node node) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                landPane.getChildren().add(node);
            }
        });
    }

    public void clear(Node node) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                landPane.getChildren().remove(node);
            }
        });
    }


    @FXML
    void clickMouseOnLandPane(MouseEvent event) {
        Audio.click();

        //todo : check validation of location and card then put it in
        //Deb.print("Mouse clicked on (source : " + event.getSource() + " ): X = " + event.getX() + "  Y = " + event.getY());
        chosenLocation = locations[(int) (event.getX() / gameView.getTileWidth())][(int) (event.getY() / gameView.getTileHeight())];
        Deb.print("Location for new card: (" + (int) (event.getX() / gameView.getTileWidth()) + "," + (int) (event.getY() / gameView.getTileHeight()) + ")");

        if (chosenCard != null) {
            if (elixir >= getCost()) {
                if (chosenLocation.isEmpty()) {
                    System.out.println("Creating card...");
                    Card card = createCard(chosenLocation);
                    System.out.println(card);
                    elixir -= getCost();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (card instanceof Fightable)
                                landPane.getChildren().add(((Fightable) card).getCurrentImage());
                            else if (card instanceof Arrows){
                                //ToDo: some errs :/
                                //new Throwable("Arrow", 1000, new Location(4, 5), chosenLocation, (this));
                            }

                        }
                    });
                    Deb.print("Class : GameController | method : clickMouseOnLandPane " +
                            "| new card added to landPane.");
                    resetCardAndLocation();
                }
            }
        }
    }

    public void resetCardAndLocation() {
        String theCard = cardImages.get(chosenCard);
        card.remove(theCard);
        card.add(theCard);
        chosenCard = null;
        chosenLocation = null;
        initializeCardImageViews(card);
        Deb.print("chosenCard and chosenLocation resets.");
    }

    public Image getRandomCardImage() {
        Random random = new Random();
        int randomNum = random.nextInt() % 3;
        int i = 0;
        for (Map.Entry<Image, String> entry : cardImages.entrySet()) {
            if (cardImageViewsContain(entry.getKey()))
                continue;
            if (i == randomNum)
                return entry.getKey();
            i++;
        }
        return null;
    }

    private boolean cardImageViewsContain(Image image) {
        for (int i = 0; i < 4; i++) {
            if (cardImageViews[i].getImage().equals(image))
                return true;
        }
        return false;
    }


    public Card createCard(Location location) {
        Card card = switch (cardImages.get(chosenCard)) {
            case "Archers" ->       new Archers     (board, Level.ONE, location, Team.A, this);
            case "BabyDragon" ->    new BabyDragon  (board, Level.ONE, location, Team.A, this);
            case "Barbarian" ->     new Barbarian   (board, Level.ONE, location, Team.A, this);
            case "Giant" ->         new Giant       (board, Level.ONE, location, Team.A, this);
            case "MiniPEKKA" ->     new MiniPEKKA   (board, Level.ONE, location, Team.A, this);
            case "Valkyrie" ->      new Valkyrie    (board, Level.ONE, location, Team.A, this);
            case "Wizard" ->        new Wizard      (board, Level.ONE, location, Team.A, this);
            case "Cannon" ->        new Cannon      (board, Level.ONE, location, Team.A, this);
            case "InfernoTower" ->  new InfernoTower(board, Level.ONE, location, Team.A, this);
            case "Arrows" ->        new Arrows      (board, Level.ONE, location, Team.A);
            case "Fireball" ->      new Fireball    (board, Level.ONE, location, Team.A);
            case "Rage" ->          new Rage        (board, Level.ONE, location, Team.A);
            default -> null;
        };
        //Deb.print("Class : GameController | method : createCard | new card created.");
        if (card == null) {
            System.out.println(cardImages.get(chosenCard) + " is not valid!");
            return null;
        }

        if (card instanceof Fightable)
            board.addAFightable((Fightable) card);

        Deb.print(cardImages.get(chosenCard) + " was created");
        return card;
    }

    public int getCost() {
        return switch (cardImages.get(chosenCard)) {
            case "Archers", "Rage", "Arrows" -> 3;
            case "BabyDragon", "MiniPEKKA", "Valkyrie", "Fireball" -> 4;
            case "Barbarian", "Wizard", "Giant", "InfernoTower" -> 5;
            case "Cannon" -> 6;
            default -> 0;
        };
    }


    @FXML
    private ImageView cardImageView1;

    @FXML
    private ImageView cardImageView3;

    @FXML
    private ImageView cardImageView2;

    @FXML
    private ImageView cardImageView4;

    @FXML
    private ImageView cardImageView5;


    @FXML
    void clickMouseOnCard(MouseEvent event) {
        System.out.print("Selecting ");
        Audio.click();
        chosenImageView = ((ImageView) event.getSource());
        chosenCard = chosenImageView.getImage();
        System.out.println(chosenCard);
    }

    public void setter(Board board, User user, Bot bot) {


        this.board = board;
        locations = this.board.getLocations();

        this.user = user;
        this.deck = user.getDeck();
        this.bot = bot;
        bot.setter(this, board, user.getLevel()); //ToDo: commented just for testing end of game.

        //System.out.println("locations" + Arrays.deepToString(locations));


        /*
         * Initialize
         */
        System.out.println("\ninitializing...\ncreating card images...");
        createCardImages();
        System.out.println("cardImages were made...");
        startTimeTimer();
        startElixirTimer();
        timeProgressBar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int minutes =(int) (t1.doubleValue()*3);
                String min = ((minutes/10 == 0)?"0" : "" )+minutes;
                int seconds = ((int) (t1.doubleValue()*180))%60;
                String sec = ((seconds/10 == 0)?"0" : "" )+seconds;
                timeText.setText(min+" : "+sec);
            }
        });

        elixirProgressBar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String elText = ""+(int)(t1.doubleValue()*10);
                elixirText.setText(elText);
            }
        });


        initializeTowers(user.getLevel(), board);
    }

    private void initializeTowers(Level level, Board board) {


        King aKing = new King(board, level, new Location(4, 5), Team.A, this);
        Platform.runLater(() -> landPane.getChildren().add(aKing.getCurrentImage()));
        Platform.runLater(() -> aKing.setProgressHP(0.95));

        Queen aQueenOne = new Queen(board, level, new Location(8, 1), Team.A, this);
        Platform.runLater(() -> landPane.getChildren().add(aQueenOne.getCurrentImage()));

        Queen aQueenTwo = new Queen(board, level, new Location(8, 13), Team.A, this);
        Platform.runLater(() -> landPane.getChildren().add(aQueenTwo.getCurrentImage()));


        King bKing = new King(board, level, new Location(29, 5), Team.B, this);
        Platform.runLater(() -> landPane.getChildren().add(bKing.getCurrentImage()));

        Queen bQueenOne = new Queen(board, level, new Location(25, 1), Team.B, this);
        Platform.runLater(() -> landPane.getChildren().add(bQueenOne.getCurrentImage()));

        Queen bQueenTwo = new Queen(board, level, new Location(25, 13), Team.B, this);
        Platform.runLater(() -> landPane.getChildren().add(bQueenTwo.getCurrentImage()));


        board.addAFightable(aKing, aQueenOne, aQueenTwo);
        board.addBFightable(bKing, bQueenOne, bQueenTwo);
    }


    @FXML
    private Text redCrowns;

    @FXML
    private Text blueCrowns;


    public void addToRedCrowns(){
        int reds = Integer.parseInt(redCrowns.getText());
        redCrowns.setText(reds + 1 + "");
    }

    public void addToBlueCrowns(){
        int blues = Integer.parseInt(blueCrowns.getText());
        blueCrowns.setText(blues + 1 + "");
    }

    public void endGameOutside(Team loser){
        isGameOver = true;
        this.loser = loser;


        if (loser == Team.A){
            a.a("Bot Won!");

            user.addLose();

            endGameText.setText("GAME OVER!\n" +
                    "Bot Won!");

            blueCrowns.setText("3");
        }
        else {
            a.a("User Won!");
            endGameText.setText("GAME OVER!\n" +
                    "User Won!\n" +
                    "click here to end.");

            user.addWin();

            redCrowns.setText("3");
        }

        endGameText.setOnMouseClicked(this::endGame);
        endGameText.setBoundsType(TextBoundsType.VISUAL);
        System.out.println(endGameText);

        board.endFightables();
        bot.inactive();

    }

    @FXML
    private Text endGameText;


    @FXML
    void endGame(MouseEvent event) {


        switchToScene("../../Menu/View/MainMenu.fxml").setUser(user);
    }

    public Menu.Controller switchToScene(String sceneName){

        //System.out.println(event.getEventType() + " on " + event.getTarget());

        Audio.click();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = (Stage)(redCrowns).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("MainMenu");
            stage.show();

            if (sceneName.contains("Menu")) {
                return loader.getController();
            }
            /*
            Parent root = new FXMLLoader(getClass().getResource("LogIn.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
             */
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
