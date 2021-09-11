package com.newrelic.codingchallenge;
import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        int portNumber = 4000;

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
        ) {


        }
    }
}