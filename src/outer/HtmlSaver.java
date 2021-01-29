package outer;

import useCases.EventManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Event;

/** Class which exports an html file.
 */

public class HtmlSaver {

    /** Formats and saves html file given a ArrayList of string events.
     *
     * @param events an ArrayList of strings of events
     * @param eventManager the current eventManager that handles the events in the program
     */
    public void saveHtml(List<String> events, EventManager eventManager) {
        File filepath = new File("schedule.html");

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
            bw.write("<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "body {" +
                    "background-color: powderblue; " +
                    "font-family: Courier New; " +
                    "margin: 50px;} " +
                    "h1 {" +
                    "text-align: center; " +
                    "color: darkblue;} " +
                    "p {" +
                    "border: 2px solid black; " +
                    "padding: 30px;}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<h1><u>Your Event Schedule:</u></h1>");
            for (String event : events) {
                Event e = eventManager.getEvent(event);
                bw.write("<p>" + e.toString() + "</p>");
            }
            bw.write("</body></html>");
            bw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
}
