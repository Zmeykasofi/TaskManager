package ru.netology.javacore.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "127.0.0.1";


    public static void main(String[] args) {

        while (true) {
            try (Socket socket = new Socket(HOST, PORT);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Введите тип операции (ADD или REMOVE) и название задачи через пробел");
                String input = reader.readLine();
                String[] inputSplit = input.split(" ");
                String type = inputSplit[0].toUpperCase();
                String task = inputSplit[1];
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Data dataEntry = new Data(type, task);
                String requestToServer = gson.toJson(dataEntry);
                System.out.println(requestToServer);
                out.println(requestToServer);
                System.out.println(in.readLine());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

