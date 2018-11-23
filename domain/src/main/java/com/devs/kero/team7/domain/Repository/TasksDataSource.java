package com.devs.kero.team7.domain.Repository;

import com.devs.kero.team7.domain.entities.Task;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface TasksDataSource {

    Observable<List<Task>> getAllTasks();

    Observable<Task> getTaskById(long Id);
    Observable<List<Task>> search(String searchstring);
    Observable<List<Task>> searchActive(String searchstring);
    Observable<List<Task>> searchComplete(String searchstring);
    Observable<List<Task>> getCompleteTasks();
    Observable<List<Task>> getActiveTasks();

    long AddTask(Task task);

    Completable UpdateTask(Task task);

    Completable DeleteTask(Task task);
    Completable DeletAllTsks();
    Completable  DeletTasks(List<Task> tasks);
    Completable DeleteActiveTasks();
    Completable DeleteCompletTasks();





}
