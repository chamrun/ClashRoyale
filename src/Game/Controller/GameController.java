package Game.Controller;

import Debugging.Deb;
import Game.Model.*;
import Game.Model.Buildings.Cannon;
import Game.Model.Buildings.InfernoTower;
import Game.Model.Soldiers.*;
import Game.Model.Spells.Arrows;
import Game.Model.Spells.Fireball;
import Game.Model.Spells.Rage;
import Game.View.GameView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.util.*;


public class GameController {
    private String[] cards = {"Archers", "MiniPEKKA", "Giant", "BabyDragon", "Valkyrie", "Wizard", "Arrows", "Rage"};
    private Location[][] locations;
    private HashMap<Image, String> cardImages = new HashMap<>();
    private Image chosenCard = null;
    private Timer timer = new Timer();
    private final int FRAMES_PER_SECOND = 1;
    private boolean isGameOver = false;
    private int gameTime = 3 * 60;
    private boolean doubleElixir = false;
    private long elixirTime = 5 * 1000;
    private ImageView[] cardImageViews = new ImageView[5];
    private ImageView chosenImageView;
    private Location chosenLocation;
    private Board board;
    private int count = 0;
    private LinkedList<String> card;
    private int elixir = 4;

    @FXML
    private ProgressBar elixirProgressBar;


    @FXML
    private Text elixirText;

    @FXML
    private Text timeText;


    public void setBoard(Board board) {
        this.board = board;
        locations = board.getLocations();
    }

    private GameView gameView;


    public GameController() {
        this.gameView = new GameView(this);
    }

    public void initialize() {
        createCardImages();
        startTimeTimer();
        startElixirTimer();
//        timeText.textProperty().bind(timeProgressBar.progressProperty());

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
                            System.out.println(i);
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
        card = new LinkedList<>();
        for (String name : cards) {
            switch (name) {
                case "Archers":
                    cardImages.put(new Image("Cards/Archer.png"), name);
                    break;
                case "BabyDragon":
                    cardImages.put(new Image("Cards/babyDragon.png"), name);
                    break;
                case "Arrows":
                    cardImages.put(new Image("Cards/Arrows.png"), name);
                    break;
                case "Barbarian":
                    cardImages.put(new Image("Cards/barbarian.png"), name);
                    break;
                case "Cannon":
                    cardImages.put(new Image("Cards/Cannon.png"), name);
                    break;
                case "Fireball":
                    cardImages.put(new Image("Cards/Fireball.png"), name);
                    break;
                case "Giant":
                    cardImages.put(new Image("Cards/Giant.png"), name);
                    break;
                case "Inferno":
                    cardImages.put(new Image("Cards/inferno.png"), name);
                    break;
                case "MiniPEKKA":
                    cardImages.put(new Image("Cards/PEKKA.png"), name);
                    break;
                case "Rage":
                    cardImages.put(new Image("Cards/Rage.png"), name);
                    break;
                case "Valkyrie":
                    cardImages.put(new Image("Cards/Valkyrie.png"), name);
                    break;
                case "Wizard":
                    cardImages.put(new Image("Cards/Wizard.png"), name);
                    break;
            }
            card.add(name);
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

    public void setProgress(ProgressBar progressBar){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(1);
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
//        //todo : check validation of location and card then put it in
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
        //todo : check validation of location and card then put it in
        Deb.print("Mouse clicked on (source : " + event.getSource() + " ): X = " + event.getX() + "  Y = " + event.getY());
        chosenLocation = locations[(int) (event.getX() / gameView.getTileWidth())][(int) (event.getY() / gameView.getTileHeight())];
        if (chosenCard != null) {
            if (elixir >= getCost()) {
                if (chosenLocation.isEmpty()) {
                    Card card = createCard(chosenLocation);
                    elixir -= getCost();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            landPane.getChildren().add(((Fightable) card).getCurrentImage());
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
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                chosenImageView.setImage(cardImageViews[4].getImage());
//                cardImageViews[4].setImage(getRandomCardImage());
//            }
//        });

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

    @FXML
    void moveMouseOnLandPane(MouseEvent event) {

        //todo : check validation of location and card then highlight it.
//        Deb.print("Mouse moved on (source : " + event.getSource() + " ): X = " + event.getX() + "  Y = " + event.getY());
    }

    public Card createCard(Location location) {
        Card card = null;
        switch (cardImages.get(chosenCard)) {
            case "Archers":
                card = new Archers(board, Level.ONE, location, Team.B, this);
                break;
            case "BabyDragon":
                card = new BabyDragon(board, Level.ONE, location, Team.B, this);
                break;
            case "Barbarian":
                card = new Barbarian(board, Level.ONE, location, Team.B, this);
                break;
            case "Giant":
                card = new Giant(board, Level.ONE, location, Team.B, this);
                break;
            case "MiniPEKKA":
                card = new MiniPEKKA(board, Level.ONE, location, Team.B, this);
                break;
            case "Valkyrie":
                card = new Valkyrie(board, Level.ONE, location, Team.B, this);
                break;
            case "Wizard":
                card = new Wizard(board, Level.ONE, location, Team.B, this);
                break;
            case "Cannon":
                card = new Cannon(board, Level.ONE, location, Team.B);
                break;
            case "InfernoTower":
                card = new InfernoTower(board, Level.ONE, location, Team.B);
                break;
            case "Arrows":
                card = new Arrows(board, location, Level.ONE, Team.B);
                break;
            case "Fireball":
                card = new Fireball(board, location, Level.ONE, Team.B);
                break;
            case "Rage":
                card = new Rage(board, location, Level.ONE, Team.B);
                break;
        }
        Deb.print("Class : GameController | method : createCard | new card created.");
        return card;
    }

    public int getCost() {
        switch (cardImages.get(chosenCard)) {
            case "Archers":
            case "Rage":
            case "Arrows":
                return 3;
            case "BabyDragon":
            case "MiniPEKKA":
            case "Valkyrie":
            case "Fireball":
                return 4;
            case "Barbarian":
            case "Wizard":
            case "Giant":
            case "InfernoTower":
                return 5;
            case "Cannon":
                return 6;
        }
        return 0;
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
        chosenImageView = ((ImageView) event.getSource());
        chosenCard = chosenImageView.getImage();
    }

}
