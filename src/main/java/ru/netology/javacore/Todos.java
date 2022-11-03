package ru.netology.javacore;
import java.util.Set;
import java.util.TreeSet;

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
        StringBuilder tasksString = new StringBuilder();
        for (String s : tasks) {
            tasksString.append(s);
            tasksString.append(" ");
        }
        return tasksString.toString().trim();
    }

    public Set<String> getTasks() {
        return tasks;
    }

    public void setTasks(Set<String> tasks) {
        this.tasks = tasks;
    }

}
