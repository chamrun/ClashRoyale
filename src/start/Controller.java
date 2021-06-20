package start;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    public void openSignUp(MouseEvent event) {
        System.out.println(event.getEventType() + " on " + event.getTarget());
        switchToScene(event, "signUp.fxml");
    }

    @FXML
    public void openLogIn(MouseEvent event) {
        System.out.println(event.getEventType() + " on " + event.getTarget());
        switchToScene(event, "logIn.fxml");
    }

    @FXML
    public void resetPassword(MouseEvent event){
        System.out.println(event.getEventType() + " on " + event.getTarget());
        switchToScene(event, "resetPassword.fxml");
    }

    public void switchToScene(Event event, String sceneName){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            /*
            Parent root = new FXMLLoader(getClass().getResource("LogIn.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
             */
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
