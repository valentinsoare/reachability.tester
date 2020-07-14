package testingReachability;

import java.io.IOException;
import java.util.ArrayList;
import static java.lang.System.out;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String RED_BOLD = "\033[1;91m";
    public static final String WHITE = "\033[1;37m";

    public static void intro(String msg) {
        out.print(WHITE + "|" + RESET);
        for (int i = 0; i < msg.length(); i++) {
            out.print(WHITE + "-" + RESET);
        }
        out.print(WHITE + "|" + RESET);
        out.println("\n" + "|" + RED_BOLD + msg + RESET + "|");
        out.print(WHITE + "|" + RESET);

        for (int j = 0; j < msg.length(); j++) {
            out.print(WHITE + "-" + RESET);
        }
        out.print(WHITE + "|" + RESET);
        out.println();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        intro("**Reachability App**");
        long startTime = System.currentTimeMillis();
        ArrayList<String> addresses = new ArrayList<String>();
        ArrayList<Integer> numberOfPackets = new ArrayList<>();
        runningActions reachability = new runningActions(addresses, numberOfPackets,"Testing reachability with ICMP messages");
        out.println("\n\t\t" + reachability.getDescription());
        reachability.getIntegersArrayListByItems();
        reachability.printList();
        reachability.reaching();
        long endTime = System.currentTimeMillis();
        out.println("\n\tExecution time, " + (endTime - startTime)/1000 + " seconds.");
    }
}
