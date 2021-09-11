package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.Cache.Cache;
import com.newrelic.codingchallenge.Log.FileLogger;
import com.newrelic.codingchallenge.Model.Message;

import java.net.*;
import java.io.*;
import java.util.concurrent.CompletableFuture;


public class ServerThread extends Thread {
    private Socket socket = null;
    private FileLogger fileLogger = null;
    private Cache cache = null;

    public ServerThread(Socket socket, FileLogger fileLogger, Cache cache) {
        this.socket = socket;
        this.fileLogger = fileLogger;
        this.cache = cache;
    }

    @Override
    public void run() {
        readLine();
    }

    public CompletableFuture<Void> readLine() {
        return CompletableFuture.supplyAsync(() -> {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            ) {
                Message message = new Message(in.readLine());

                return message;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).thenAccept((message) -> {

            if (message.isTerminate()) {
                System.exit(0);
            }

            if (!cache.add(message.getNumber())) {
                fileLogger.getDuplicateCounter().getAndIncrement();

            } else if (message.isValid()) {
                fileLogger.getUniqueTotal().getAndIncrement();
                fileLogger.getUniqueCounter().getAndIncrement();
                ServerProtocol sp = new ServerProtocol();
                sp.processInput(message);
            } else {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    };
}
