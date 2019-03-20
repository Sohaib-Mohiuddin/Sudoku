
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Scanner;

public class tester {

    public File file = new File("savefile2.txt");
    public int[] values;

    public void saveToFile() {

        try (FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            out.println(2);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try {
            int token1;
            Scanner scanner = new Scanner(file);          
            List<Integer> temps = new ArrayList<Integer>();
            while (scanner.hasNext()) {
                token1 = scanner.nextInt();
                temps.add(token1);
            }
            scanner.close();

            for (int s = 0; s < temps.size(); s++) {
                System.out.println(temps.get(s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        tester test = new tester();
        //test.saveToFile();
        test.loadFromFile();
    }
}