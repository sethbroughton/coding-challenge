package com.newrelic.codingchallenge.Log;

import java.io.File;

public class FileHandler {

    private static FileHandler instance = null;

    private final String filename = "numbers.log";

    private FileHandler() {
        deleteFile();
    }

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

    private void deleteFile() {
        File file = new File(filename);
        file.delete();
    }

    public String getFilename() {
        return filename;
    }
}
