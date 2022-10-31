package ru.netology.javacore;

import ru.netology.javacore.clients.ClientRequest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private int port;
    private Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ClientRequest clientRequest = new ClientRequest();
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                    while (true) {
                        String input = in.readLine();
                        System.out.println(input);
                        clientRequest.requestToString(input);
                        switch (clientRequest.getType()) {
                            case ("ADD"):
                                todos.addTask(clientRequest.getTask());
                                break;
                            case ("REMOVE"):
                                todos.removeTask(clientRequest.getTask());
                                break;
                        }
                        out.println("Список Ваших задач: " + todos.getAllTasks());
                    }
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
