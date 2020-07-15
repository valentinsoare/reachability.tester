package testingReachability;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import static java.lang.System.*;

public class runningActions {

        static final String RESET = "\u001B[0m";
        static final String RED = "\033[0;31m";
        private ArrayList<String> addresses;
        private ArrayList<Integer> numberOfPackets;
        private String description;
        private static final Logger myLogger = Logger.getLogger("Errors and running information");

        public runningActions(ArrayList<String> addresses, ArrayList<Integer> numberOfPackets, String description) {
            this.addresses = addresses;
            this.description = description;
            this.numberOfPackets = numberOfPackets;
        }

        public void reaching() throws IOException {
            for (int i = 0; i < this.addresses.size(); i++) {
                Process reach = Runtime.getRuntime().exec(new String[]{"bash", "-c", "ping " + " -c " + this.numberOfPackets.get(i) + " " + this.addresses.get(i)});
                BufferedReader reader = new BufferedReader(new InputStreamReader(reach.getInputStream()));
                if (i > 0) {
                    out.println();
                }
                out.println("***Trying to " + "ping " + RED + this.addresses.get(i) + RESET);
                String line;
                boolean check = true;

                while ((line = reader.readLine()) != null) {
                    out.println(line);
                }

            }
        }

        private static boolean isInteger(String s) {
            boolean value = true;

            try {
                Integer.parseInt(s);
            } catch (Exception e) {
                value = false;
            }

            return value;
        }

        private boolean checkDnsIp(String input) throws IOException {
            Process proc1 = Runtime.getRuntime().exec(new String[]{"bash", "-c", "dig " + input + " | " + "awk 'NR==14{print $5}'"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc1.getInputStream()));

            String patt = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";
            String reg = patt + "\\." + patt + "\\." + patt + "\\." + patt;
            Pattern t = Pattern.compile(reg);

            return t.matcher(reader.readLine()).matches();
        }

        private void writeArray (int n) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int i = 0;
            boolean check = false;
            while (i < n) {
                out.print("[" + (i + 1) + "] Address: - ");
                String input = reader.readLine().trim();

                if (input.equals("") || isInteger(input) || !checkDnsIp(input)) {
                    myLogger.severe("Invalid entry. Valid DNS name or IP address is needed.");
                    out.printf("%n%n");
                    continue;
                }

                this.addresses.add(i, input);

                do {
                    out.print("[" + (i + 1) + "] Number of packets: - ");
                    try {
                        int number = Integer.parseInt(reader.readLine().trim());
                        this.numberOfPackets.add(i, number);
                        check = true;
                    } catch (Exception e) {
                        myLogger.severe("Invalid entry. We need an integer value.");
                        out.printf("%n%n");
                    }
                } while (!check);
                i++;
            }
        }

        public void getIntegersArrayListByItems () {
            BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
            boolean exiting = true;
            out.println();
            while (exiting) {
                out.print("|--How many items you want in the list: ");
                try {
                    int n = Integer.parseInt(scan.readLine().trim());
                    out.println();
                    writeArray(n);
                    exiting = false;
                } catch (Exception e) {
                    myLogger.severe("Invalid entry, integer is needed.");
                    out.printf("%n%n");
                }
            }
        }

        public ArrayList<String> printList () {
            out.print(RED + "\n[ALL] Addresses: " + RESET + "[ ");
            for (int i = 0; i < this.addresses.size(); i++) {
                out.print(this.addresses.get(i));
                if (i < this.addresses.size() - 1) {
                    out.print(", ");
                }
            }
            out.print(" ]\n\n");
            return this.addresses;
        }

        public String getDescription() {
            return description;
        }
}
