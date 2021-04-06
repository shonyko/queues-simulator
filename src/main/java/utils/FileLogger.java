package utils;

import interfaces.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private FileWriter fileWriter;

    public FileLogger(String path) {
        File file = new File(path);
        if(!file.exists()) {
            try {
                file.createNewFile();
                file.setWritable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            fileWriter = new FileWriter(file, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(String message) {
        try {
            fileWriter.append(message);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
