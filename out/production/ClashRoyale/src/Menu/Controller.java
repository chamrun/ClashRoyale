package Menu;

import Accounts.Database;
import Accounts.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import jdk.jfr.Event;

public class Controller {

    @FXML Button test;
    @FXML Text hello;
    User user;


    @FXML
    public void setUser(Event event, User user) {
        /*
        this.user = user;
        System.out.println(user.getName() + " opened main menu.");
        hello.setText("Hello " + user.getName());

         */
        user = new User(new Database("localhost", "sa", "SQLpass", "test.dbo.users"), "Test");
    }


    public void setUser(User user) {

        this.user = user;
        System.out.println(user.getName() + " opened main menu.");
        hello.setText("Hello " + user.getName());

    }


    /*
    @FXML void test(){
        System.out.println(user.getEmail());
    }

     */
}
