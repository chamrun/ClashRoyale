package Accounts;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Database {

    Statement statement;
    String tableName;

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

    public User signUp(String userName, String password){
        try{


            //Check if userName is not new, return null
            //if(){
            //  return null;
            // }

            String signupCmd = "INSERT INTO " + tableName + "\n" +
                    "(uname, pass, mcode) VALUES " +
                    "(" + userName + ", " + getMd5(password) + ", 1)";

            ResultSet resultSet = statement.executeQuery(signupCmd);

            // Print results from select statement
            while (resultSet.next()) {

                if (resultSet.getString("pass").startsWith(password)){
                    return new User(userName, password);
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


    public User tryLogIn(String userName, String password){
        System.out.println(userName + " is trying to login with: " + password);


        try{

            String cmd = "SELECT * FROM test.dbo.users\n" +
                    "WHERE uname = " + "'" + userName + "'";

            ResultSet resultSet = statement.executeQuery(cmd);

            // Print results from select statement
            while (resultSet.next()) {

                if (resultSet.getString("pass").startsWith(password)){
                    return new User(userName, password);
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


}
