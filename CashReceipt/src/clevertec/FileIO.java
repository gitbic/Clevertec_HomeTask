package clevertec;

import java.io.*;
import java.util.Scanner;

public final class FileIO {

    public String read(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + filePath + " does not exist."
                    + "\nThe program will be finished.");
            System.exit(0);
        }
        return sb.toString();
    }

    public void write(String filePath, String text) {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(text);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
