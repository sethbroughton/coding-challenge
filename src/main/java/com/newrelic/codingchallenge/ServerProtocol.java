package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.Log.FileHandler;
import com.newrelic.codingchallenge.Model.Message;

import java.io.*;

public class ServerProtocol {

    FileHandler fileHandler = FileHandler.getInstance();

    public void processInput(Message message) {

        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileHandler.getFilename(), true))) {
            String newLine = message.getNumber()+"\n";
            byte[] arr = newLine.getBytes();
            bos.write(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
