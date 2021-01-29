
import controllers.LoginSystem;
import outer.SaveReader;

import java.io.IOException;

public class EventScheduleSystem {
    public static void main(String[] args) throws IOException {
        SaveReader saveReader = new SaveReader();
        LoginSystem loginSystem = saveReader.getSystem("data.ser");

        loginSystem.run();
        saveReader.saveSystem(loginSystem);
    }
}
