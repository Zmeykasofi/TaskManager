package ru.netology.javacore;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {
    Todos todos;

    @BeforeEach
    void initializeData() {
        todos = new Todos();
    }

    @AfterEach
    void resetData() {
        todos = null;
    }

    @Test
    @DisplayName("Проверка добавления задач")
    void addTask() {
        todos.addTask("1");
        todos.addTask("2");
        todos.addTask("3");

        Assertions.assertEquals("1 2 3", todos.getAllTasks());
    }

    @Test
    @DisplayName("Проверка добавления больше 7 задач")
    void addMoreTasks() {
        for (int i = 0; i < 9; i++) {
            todos.addTask("Task" + i);
        }
        Assertions.assertEquals(7, todos.getTasks().size());
    }

    @Test
    @DisplayName("Проверка удаления задачи")
    void removeTask() {
        todos.addTask("B");
        todos.addTask("C");
        todos.addTask("A");
        todos.removeTask("B");

        Assertions.assertEquals("A C", todos.getAllTasks());
    }

    @Test
    @DisplayName("Проверка вывода задач в алфавитном порядке")
    void getAllTasks() {
        todos.addTask("C");
        todos.addTask("A");
        todos.addTask("B");

        Assertions.assertEquals("A B C", todos.getAllTasks());
    }
}