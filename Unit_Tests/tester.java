package Unit_Tests;

import java.io.*;
import java.util.Scanner;


public class tester {

    public File file = new File("savefile.txt");

    public void saveToFile() {

        try(Scanner scanner = new Scanner(file); 
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(2);

            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {

    }

    public static void main (String[] args) {
        tester test = new tester();
        //test.saveToFile();
        test.loadFromFile();
    }
}