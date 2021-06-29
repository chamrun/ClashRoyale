package Accounts;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {



    @FXML
    public void openSignUp(Event event){
        ((Text) event.getTarget()).setFill(Color.RED);
        System.out.println(event.getEventType() + " on " + event.getTarget());
        switchToScene(event, "Scenes/signUp.fxml");
    }

    @FXML
    public void openLogIn(Event event){
        System.out.println(event.getEventType() + " on " + event.getTarget());
        switchToScene(event, "Scenes/logIn.fxml");
    }

    @FXML
    public void openResetPassword(Event event){
        System.out.println(event.getEventType() + " on " + event.getTarget());
        switchToScene(event, "Scenes/resetPassword.fxml");

    }

    @FXML
    public void confirmEmailToReset(Event event){
        System.out.println(event.getEventType() + " on " + event.getTarget());
        switchToScene(event, "Scenes/resetPassword2.fxml");
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
