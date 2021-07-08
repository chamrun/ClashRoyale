package Accounts;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Collection;

public class Model {


    public static User tryLogIn(String email, String password){
        System.out.println(email + " is trying to login with: " + password);

        try{

            Connection connection = DriverManager.getConnection
                    ("jdbc:sqlserver://localhost;user=sa;password=SQLpass;");

            Statement statement = connection.createStatement();

            String cmd = "SELECT pass FROM test.dbo.users\n" +
                    "WHERE uname = 'ali'";

            ResultSet resultSet = statement.executeQuery(cmd);

            // Print results from select statement
            while (resultSet.next()) {

                if (resultSet.getString("pass").equals(password)){
                    return new User(email, password);
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


        public static boolean signUp(String email, String password){

        File directory = new File("Accounts/UserFiles");

        Collection<File> files = FileUtils.listFiles(directory,
                new String[] {"txt"}, true);


        try {
            for (File f : files) {

                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);

                try {
                    User temp;
                    temp = (User) ois.readObject();
                    if (email.equals(temp.getEmail())){
                        return false;
                    }
                    fis.close();
                    ois.close();

                } catch (EOFException ignored){
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        File file = new File("UserFiles\\" + email + ".txt");

        try {
            FileUtils.touch(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream("UserFiles\\" + email + ".txt")){
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(new User(email, password));
            fos.close();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(email + " was signed up");
        return true;

    }


}
