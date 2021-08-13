package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static final int PORT = 24897;
    public static final String HOSTNAME = "127.0.0.1";

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String consoleReadString() {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static void main(String[] args) {
        Server.server(HOSTNAME, PORT);
    }
}
