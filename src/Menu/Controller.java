package Menu;

import Accounts.Database;
import Accounts.User;
import javafx.fxml.FXML;
import jdk.jfr.Event;

public class Controller {


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

    }


    /*
    @FXML void test(){
        System.out.println(user.getEmail());
    }

     */
}
