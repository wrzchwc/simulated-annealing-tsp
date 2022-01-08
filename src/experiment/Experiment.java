package experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Experiment {
    private static final String SETUP = "src/experiment/setup.txt";

    public static void run() {
        Map<String, Integer> setup = getSetup();
        for (String instance : setup.keySet()) {
            System.out.println(instance);
        }

    }

    private static Map<String, Integer> getSetup() {
        Map<String, Integer> setup = new HashMap<>();
        File file = new File(SETUP);
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String[] line = input.nextLine().split(" ");
                setup.put(line[0], Integer.parseInt(line[1]));
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Setup file not found!");
        }
        return setup;
    }
}
