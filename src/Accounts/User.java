package Accounts;

import java.io.Serializable;

public class User implements Serializable {
    private final String email;
    private final String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User getUser(String email, String password) {
        if (!email.equalsIgnoreCase(this.email)){
            return null;
        }
        if (!password.equals(this.password)){
            return null;
        }
        return this;
    }

    public String getEmail() {
        return email;
    }
}
