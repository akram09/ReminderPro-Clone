package com.devs.kero.team7.data.repositories;

import android.content.Context;

import com.devs.kero.team7.data.Db.ReminderDatabase;
import com.devs.kero.team7.data.Db.dao.TaskDao;
import com.devs.kero.team7.data.dataModel.TaskData;
import com.devs.kero.team7.data.mapper.TaskMapper;
import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.entities.Task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

public class TaskRepository implements TasksDataSource {
     Context context ;
     TaskMapper taskMapper ;
     TaskDao  Dao ;

    @Inject
    public TaskRepository(Context context, TaskMapper taskMapper) {
        Dao= ReminderDatabase.getDatabase(context).getTaskDao();
        this.context = context;
        this.taskMapper = taskMapper;
    }

    @Override
    public Observable<List<Task>> getAllTasks() {

        return Dao.getAllTasks().toObservable().map(new Function<List<TaskData>, List<Task>>() {
            @Override
            public List<Task> apply(List<TaskData> taskData) throws Exception {
                List<Task> tasks = new ArrayList<>();
                for (TaskData ta: taskData
                     ) {
                   tasks.add(taskMapper.from(ta));
                }
                return tasks ;
            }
        });
    }

    @Override
    public Observable<Task> getTaskById(long Id) {
        return Dao.getTaskById(Id).toObservable().map(new Function<TaskData, Task>() {
            @Override
            public Task apply(TaskData taskData) throws Exception {
                return taskMapper.from(taskData);
            }
        });

    }

    @Override
    public Observable<List<Task>> search(String searchstring) {
        return Dao.search(searchstring).toObservable().map(new Function<List<TaskData>, List<Task>>() {
            @Override
            public List<Task> apply(List<TaskData> taskData) throws Exception {
                List<Task> tasks = new ArrayList<>();
                for (TaskData ta: taskData
                        ) {
                    tasks.add(taskMapper.from(ta));
                }
                return tasks ;
            }
        });
    }

    @Override
    public Observable<List<Task>> searchActive(String searchstring) {
        return Dao.searchActive(searchstring).toObservable().map(new Function<List<TaskData>, List<Task>>() {
            @Override
            public List<Task> apply(List<TaskData> taskData) throws Exception {
                List<Task> tasks = new ArrayList<>();
                for (TaskData ta: taskData
                        ) {
                    tasks.add(taskMapper.from(ta));
                }
                return tasks ;
            }
        });
    }

    @Override
    public Observable<List<Task>> searchComplete(String searchstring) {
        return Dao.searchComplete(searchstring).toObservable().map(new Function<List<TaskData>, List<Task>>() {
            @Override
            public List<Task> apply(List<TaskData> taskData) throws Exception {
                List<Task> tasks = new ArrayList<>();
                for (TaskData ta: taskData
                        ) {
                    tasks.add(taskMapper.from(ta));
                }
                return tasks ;
            }
        });
    }

    @Override
    public Observable<List<Task>> getCompleteTasks() {
        return  Dao.getCompleteTasks().toObservable().map(new Function<List<TaskData>, List<Task>>() {
            @Override
            public List<Task> apply(List<TaskData> taskData) throws Exception {
                List<Task> tasks = new ArrayList<>();
                for (TaskData ta: taskData
                        ) {
                    tasks.add(taskMapper.from(ta));
                }
                return tasks ;
            }
        });
    }

    @Override
    public Observable<List<Task>> getActiveTasks() {
        return Dao.getActiveTaks().toObservable().map(new Function<List<TaskData>, List<Task>>() {
            @Override
            public List<Task> apply(List<TaskData> taskData) throws Exception {
                List<Task> tasks = new ArrayList<>();
                for (TaskData ta: taskData
                        ) {
                    tasks.add(taskMapper.from(ta));
                }
                return tasks ;
            }
        });
    }

    @Override
    public Completable AddTask(final Task task) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Dao.insertTask(taskMapper.mapToAdd(task));
            }
        });
    }

    @Override
    public Completable UpdateTask(final Task task) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Dao.updateTask(taskMapper.to(task));
            }
        });
    }

    @Override
    public Completable DeleteTask(final Task task)
    {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Dao.deleteTask(taskMapper.to(task));
            }
        });
    }

    @Override
    public Completable DeletAllTsks()
    {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Dao.deleteAllTasks();
            }
        });
    }

    @Override
    public Completable DeletTasks(final List<Task> tasks) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                List<TaskData> taskData = new ArrayList<>();
                for (Task task: tasks
                     ) {
                     taskData.add(taskMapper.to(task));
                }
                Dao.deleteTasks(taskData);
            }
        });
    }

    @Override
    public Completable DeleteActiveTasks() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Dao.deletActiveTask();
            }
        });
    }

    @Override
    public Completable DeleteCompletTasks()
    {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Dao.deletCmpleteTask();
            }
        });
    }
}
