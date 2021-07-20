package Accounts;

import Player.User;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Database {




    private Statement statement;
    private final String tableName;

    public Database(String host, String user, String password, String tableName){

        try {
            Connection connection = DriverManager.getConnection
                    //("jdbc:sqlserver://localhost;user=sa;password=SQLpass;");
                            ("jdbc:sqlserver://" + host + ";user=" + user + ";password=" + password + ";");

            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.tableName = tableName;

    }

    public User trySignUp(String userName, String password){
        try{

            String signupCmd = "INSERT INTO " + tableName + "\n" +
                    "(UserName, Password, Coins, Wins, Loses) VALUES " +
                    "('" + userName + "', '" + getMd5(password) + "', '0', '0', '0')";

            int num = 0;

            try {
                num = statement.executeUpdate(signupCmd);
            }
            catch (SQLServerException e){
                System.out.println("UserName is used before.");
            }


            if (num == 1) {
                return new User(this, userName);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return null;

    }

    public User tryLogIn(String userName, String password){
        System.out.println(userName + " is trying to login...");


        try{

            String cmd = "SELECT * FROM " + tableName +
                    " WHERE UserName = '" + userName + "'";

            System.out.println(cmd);

            ResultSet resultSet = statement.executeQuery(cmd);

            // Print results from select statement
            while (resultSet.next()) {

                String hashedInput = getMd5(password);
                String pass = resultSet.getString("Password").replace(" ", "");// Because pass is too lone, TODO: should be set (16)

                System.out.println("'" + hashedInput + "'\n" +
                        "'" + pass + "'");

                if (pass.equals(hashedInput)){
                    String deck = resultSet.getString("Deck");
                    int coins = resultSet.getInt("Coins");
                    int wins = resultSet.getInt("Wins");
                    int loses = resultSet.getInt("Loses");

                    return new User(this, userName, deck, coins, wins, loses);
                }

                //System.out.println(resultSet.getString("uname") + ":: " + resultSet.getString("mcode"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return null;
    }

    private static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String userName, int coins, int winsOrLoses, boolean isWon) {
        //ToDo: level row should be added.
        String query = "update " + tableName + " set Coins = " + coins + " where UserName = '" + userName + "'";
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }
        System.out.println(userName + "coins updated: " + coins);

        if (isWon){
            query = "update " + tableName + " set Wins = " + winsOrLoses + " where UserName = '" + userName + "'";
        }
        else {
            query = "update " + tableName + " set Loses = " + winsOrLoses + " where UserName = '" + userName + "'";
        }
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }
        System.out.println("WinsOrLoses updated.");


    }

    public void update(String userName, String newDeck) {
        String query = "update " + tableName + " set Deck = '" + newDeck + "' where UserName = '" + userName + "'";
        System.out.println(query);
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }
        System.out.println(userName + " deck updated: " + newDeck);
    }
}