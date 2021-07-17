package Game.Controller;

import Debugging.Deb;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;


public class GameController {

    @FXML
    public Pane landPane;

    @FXML
    void clickMouseOnLandPane(MouseEvent event) {
        Deb.print("Mouse clicked on (source : "+event.getSource()+" ): X = "+event.getX() + "  Y = "+event.getY());
    }

    @FXML
    void moveMouseOnLandPane(MouseEvent event) {
        Deb.print("Mouse moved on (source : "+event.getSource()+" ): X = "+event.getX() + "  Y = "+event.getY());
    }

}
