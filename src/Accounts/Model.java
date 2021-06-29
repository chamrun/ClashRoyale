package Accounts;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Collection;

public class Model {


    public static User tryLogIn(String email, String password){
        System.out.println(email + " is trying to login with: " + password);
        return null;
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
