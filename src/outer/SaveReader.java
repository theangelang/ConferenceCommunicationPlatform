package outer;

import java.io.*;
import controllers.LoginSystem;

/** Class which serializes and deserializes the program.
 */

public class SaveReader implements Serializable {

    /** Serializes the LoginSystem instance and saves to "data.ser".
     *
     * @param system the current LoginSystem that you want to serialize.
     */
    public void saveSystem(LoginSystem system) {

        try {
            String filepath = "data.ser";
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(system);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Something bad happened");
        }
    }

    /** Deserializes a file and casts to a LoginSystem.
     *
     * @param filepath a string which indicates the file to be deserialized
     * @return returns a LoginSystem which has been deserialized.
     */
    public LoginSystem getSystem(String filepath) {

        LoginSystem system = new LoginSystem();
        try{
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            system = (LoginSystem) ois.readObject();
            ois.close();
            fis.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Oh god it's not there, have a blank one");
        } catch (FileNotFoundException e) {
            System.out.println("NO FILE!");
        } catch (IOException e) {
            System.out.println("THERE WAS A BAD");
        }

        return system;
    }

}
