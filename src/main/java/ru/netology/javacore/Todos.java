package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private Set<String> tasks;
    private static final int NUMBER = 7;

    public Todos() {
        this.tasks = new TreeSet<>();
    }

    public void addTask(String task) {
        if (tasks.size() < NUMBER) {
            tasks.add(task);
        } else {
            System.out.println("Превышено максимальное количество задач!");
        }
    }


    public void removeTask(String task) {
            tasks.remove(task);
    }


    public String getAllTasks() {
        String listTasks = tasks.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(" "));

        Set<String> collectedTasks = new TreeSet<>();
        collectedTasks.addAll(Collections.singleton(listTasks));
        return collectedTasks.toString();
    }

    public Set<String> getTasks() {
        return tasks;
    }

    public void setTasks(Set<String> tasks) {
        this.tasks = tasks;
    }

}
