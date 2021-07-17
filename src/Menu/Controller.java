package Menu;

import Accounts.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    Button test;
    User user;

    public void setUser(User user) {
        this.user = user;
        System.out.println(user.getName() + " opened main menu.");
    }


    /*
    @FXML void test(){
        System.out.println(user.getEmail());
    }

     */
}
