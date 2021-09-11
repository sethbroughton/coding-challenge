package com.newrelic.codingchallenge;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServerProtocol {

    public void processInput(String theInput) {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("numbers.log", true))) {
            bw.write(theInput);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
