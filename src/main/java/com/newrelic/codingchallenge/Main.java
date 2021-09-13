package com.newrelic.codingchallenge;
import com.newrelic.codingchallenge.Cache.Cache;
import com.newrelic.codingchallenge.Log.FileLogger;

import java.net.*;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        final int PORT_NUMBER = 4000;
        final int MAX_CONNECTIONS = 5;

        boolean notTerminated = true;

        FileLogger fileLogger = new FileLogger();
        Cache cache = Cache.getInstance();

        Runnable report = new Runnable() {
            public void run() {
                System.out.println(fileLogger.getMetrics());
                fileLogger.resetPeriodCounters();
            };
        };

        ScheduledExecutorService reportThread = Executors.newScheduledThreadPool(1);
        reportThread.scheduleAtFixedRate(report, 0, 10, TimeUnit.SECONDS);

        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER, MAX_CONNECTIONS)) {
            System.out.println("Starting server...");

            while (notTerminated) {
                new ServerThread(serverSocket.accept(), fileLogger, cache).start();
            }
        } catch (IOException ioe) {
            System.err.println("Could not listen on port " + PORT_NUMBER);
            System.exit(1);
        }
    }
}