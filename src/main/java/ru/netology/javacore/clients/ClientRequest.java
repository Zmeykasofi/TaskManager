package ru.netology.javacore.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ClientRequest {
    private String type;
    private String task;

    private GsonBuilder gsonBuilder = new GsonBuilder();

    public ClientRequest() {
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

    public String[] requestToString(String json) {
        Gson gson = gsonBuilder.create();
        ClientRequest request = gson.fromJson(json, ClientRequest.class);
        this.task = request.getTask();
        this.type = request.getType();
        return new String[]{String.valueOf(type), task};
    }
}
