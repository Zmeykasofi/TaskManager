package ru.netology.javacore.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "127.0.0.1";
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            while (true) {
                System.out.println("Введите ADD для добавления задачи, REMOVE для удаления задачи:");
                String type = scanner.nextLine().toUpperCase();
                System.out.println("Введите название задачи:");

                String task = scanner.nextLine();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Data dataEntry = new Data(type, task);
                String requestToServer = gson.toJson(dataEntry);
                System.out.println(requestToServer);

                out.println(requestToServer);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

