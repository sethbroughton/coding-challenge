package com.newrelic.codingchallenge;

import java.net.*;
import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ServerThread extends Thread {
    private Socket socket = null;

    private volatile String number;

    public ServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }

//    public void run() {
//        try (
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(
//                                socket.getInputStream()));
//        ) {
//            ServerProtocol sp = new ServerProtocol();
//
//            sp.processInput(in.readLine());
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    public void run() {
        try {
            this.number = readLine().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String getNumber(){
        return this.number;
    }

    public CompletableFuture<String> readLine() {
        return CompletableFuture.supplyAsync(()-> {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            ) {
                return in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).thenApply( (number) -> {
            ServerProtocol sp = new ServerProtocol();
            sp.processInput(number);
            return number;
        });
    }

}
