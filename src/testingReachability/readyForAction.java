package testingReachability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import static java.lang.System.*;

public class readyForAction {
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";

    ArrayList<Addresses> destinations;

    public readyForAction(ArrayList<Addresses> destinations) {
        this.destinations = destinations;
    }

    public void reaching() throws IOException, InterruptedException {
        for (int i = 0; i < this.destinations.size(); i++) {
            Process reach = Runtime.getRuntime().exec(new String[]{"bash", "-c", "ping " + this.destinations.get(i)});
            reach.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(reach.getInputStream()));
            out.println("-------------");
            out.println(RED + "***Trying to: " + RESET + "PING " + this.destinations.get(i));
            String line = "";
            boolean check = true;

            if (reader.readLine() == null) {
                check = false;
                out.println(RED + "--- address is not reachable." + RESET);
            }

            while ((line = reader.readLine()) != null) {
                out.println(line);
            }

            if (check) {
                out.println("--- address is reachable.");
            }
        }
    }
}

