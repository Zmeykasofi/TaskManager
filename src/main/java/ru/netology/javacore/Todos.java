package ru.netology.javacore;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Todos {
    private static final int NUMBER = 7;
    private Set<String> tasks;

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
        collectedTasks.add(listTasks);
        return collectedTasks.toString();
    }

    public Set<String> getTasks() {
        return tasks;
    }

    public void setTasks(Set<String> tasks) {
        this.tasks = tasks;
    }

}
