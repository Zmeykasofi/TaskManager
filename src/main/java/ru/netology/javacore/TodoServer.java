package ru.netology.javacore;

import com.google.gson.Gson;
import ru.netology.javacore.clients.ClientRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ClientRequest clientRequest;
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {

                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                    String input = in.readLine();
                    System.out.println(input);
                    clientRequest = new Gson().fromJson(input, ClientRequest.class);
                    switch (clientRequest.getType()) {
                        case ("ADD"):
                            todos.addTask(clientRequest.getTask());
                            break;
                        case ("REMOVE"):
                            todos.removeTask(clientRequest.getTask());
                            break;
                    }
                    out.println("Список Ваших задач: " + todos.getAllTasks());

                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
