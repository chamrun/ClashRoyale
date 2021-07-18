package Menu;

import Accounts.Database;
import Accounts.User;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {





    @FXML
    private PieChart chart;

    @FXML
    private Text levelText;

    @FXML
    private Text coins;

    @FXML
    private ProgressBar levelBar;


    User user;


    @FXML
    public void refreshProfile(Event event){

        levelText.setText(("Level " + user.getLevel().toString()));
        coins.setText(user.getCoins() + "");

        //ToDo: just for test, should be removed


        if (chart.getData().size() != 0) {
            chart.getData().clear();

            System.out.println("Removing all from chart...");
        }

        PieChart.Data wins = new PieChart.Data("Wins", user.getWins());
        PieChart.Data loses = new PieChart.Data("Loses", user.getLoses());

        ObservableList<PieChart.Data> chartData =
                FXCollections.observableArrayList(
                        wins, loses);

        chart.getData().addAll(chartData);

        loses.getNode().setStyle("-fx-pie-color: blue");
        wins.getNode().setStyle("-fx-pie-color: red");

        chartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), ": ", data.getPieValue()
                        )
                )
        );


        levelBar.setProgress(user.getLevelProgress());



    }

    @FXML
    public void setUser(Event event, User user) {
        /*
        this.user = user;
        System.out.println(user.getName() + " opened main menu.");
        hello.setText("Hello " + user.getName());

         */
        user = new User(new Database("localhost", "sa", "SQLpass", "test.dbo.users"), "Test");
    }

    @FXML
    public void askDifficulty(Event event){
        System.out.println(event.getEventType() + " on " + event.getTarget());
        //switchToScene(event, "AskDifficulty");
        switchToScene(event, "View/Profile.fxml");
    }

    @FXML
    public void gotoMainMenu(MouseEvent event) {
        switchToScene(event, "View/MainMenu.fxml");
    }

    @FXML
    public void gotoProfile(MouseEvent event){
        //chart


        switchToScene(event, "View/Profile.fxml");


    }


    public void gotoDeck(MouseEvent event) {
        switchToScene(event, "View/Deck.fxml");
    }




    public void setUser(User user) {

        this.user = user;
        System.out.println("setUser(" + user.getName() + ")");

    }

    public void switchToScene(javafx.event.Event event, String sceneName){

        System.out.println(event.getEventType() + " on " + event.getTarget());
        System.out.println("Trying to switch to " + sceneName);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(getTitle(sceneName));
            stage.show();

            if (!sceneName.startsWith("..")){
                ((Controller) loader.getController()).setUser(user);
            }

            //ToDo: if sceneName is "Game/..", send user to it, somehow
        }
        catch (ExceptionInInitializerError e){
            System.out.println(sceneName + ": wrong?");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    private String getTitle(String sceneName) {
        String[] strings = (sceneName.replace(".fxml", "")).split("/");
        return strings[strings.length - 1];
    }

    public void logout(MouseEvent event) {
        System.out.println("logging out...");
        //are you sure?
        switchToScene(event, "../Accounts/View/login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialize of Menu.Controller");

        if (url.toString().endsWith("Profile.fxml")) {





        }
        else if (url.toString().endsWith("Deck.fxml")){
            //Load decks...
        }

    }



    /*
    @FXML void test(){
        System.out.println(user.getEmail());
    }

     */
}
