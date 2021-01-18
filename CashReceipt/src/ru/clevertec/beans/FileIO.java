package ru.clevertec.beans;

import ru.clevertec.constants.ErrorMsg;

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
            System.out.println(String.format(ErrorMsg.FSTRING_FILE_NOT_FOUND, filePath));
            System.exit(0);
        }
        return sb.toString();
    }

    public void write(String filePath, String text) {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(text);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
