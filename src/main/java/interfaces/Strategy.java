package interfaces;

import businessLogic.Server;
import models.Task;

import java.util.List;

public interface Strategy {
    boolean addTask(List<Server> servers, Task task);
}
