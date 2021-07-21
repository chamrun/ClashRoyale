package Accounts;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    Database database = new Database("localhost", "sa", "SQLpass", "ClashRoyale.dbo.Users");

    @FXML
    public TextField userName;
    @FXML
    public TextField password;
    @FXML
    private Text alertText;

    Media clickMedia = new Media(getClass().getResource("../Audio/click.wav").toExternalForm());


    public Controller(){

    }

    @FXML
    public void openSignUp(Event event){
        switchToScene(event, "View/signUp.fxml");
    }

    @FXML
    public void openLogIn(Event event){
        switchToScene(event, "View/logIn.fxml");
    }

    @FXML
    public void openResetPassword(Event event){
        switchToScene(event, "View/resetPassword.fxml");
    }

    @FXML
    public void logIn(Event event) {

        System.out.println(event.getEventType() + " on " + ((Button) event.getTarget()).getText());

        if (userName.getText().equals("")){
            alertText.setText("UserName can't be empty.");
            return;
        }
        if (password.getText().equals("")){
            alertText.setText("Password can't be empty.");
            return;
        }


        User user = database.tryLogIn(userName.getText(), password.getText());
        if (user == null) {
            System.out.println(userName.getText() + " couldn't log in.");
            alertText.setText("UserName or Password is wrong");
            return;
        }
        System.out.println(userName.getText() + " logged in.");


        switchToScene(event, "../Menu/View/MainMenu.fxml").setUser(user);

        // Here we send user to "MainMenu package"
    }

    @FXML
    public void signUp(Event event){
        System.out.println(event.getEventType() + " on " + event.getTarget());

        if (userName.getText().equals("")){
            alertText.setText("UserName can't be empty.");
            return;
        }
        if (password.getText().equals("")){
            alertText.setText("Password can't be empty.");
            return;
        }

        User user = database.trySignUp(userName.getText(), password.getText());
        if (user == null){

            System.out.println(userName.getText() + " already exists.");
            alertText.setText(userName.getText() + " already exists.");
            //Suggest logging in.

        }
        else {

            System.out.println(user + " signed up.");
            switchToScene(event, "View/logIn.fxml");


        }


    }



    @FXML
    public void confirmEmailToReset(Event event){
        System.out.println(event.getEventType() + " on " + event.getTarget());
        switchToScene(event, "View/resetPassword2.fxml");
    }


    public Menu.Controller switchToScene(Event event, String sceneName){

        System.out.println(event.getEventType() + " on " + event.getTarget());

        playClick();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(getTitle(sceneName));
            stage.show();

            if (sceneName.startsWith("..")) {
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
            System.out.println(sceneName + ": wrong?");
        }

        return null;
    }

    private String getTitle(String sceneName) {
        String[] strings = (sceneName.replace(".fxml", "")).split("/");
        return strings[strings.length - 1];
    }

    private void playClick(){
        MediaPlayer clickPlayer = new MediaPlayer(clickMedia);
        clickPlayer.play();
    }
}
