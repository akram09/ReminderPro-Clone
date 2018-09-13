package com.devs.kero.team7.data.mapper;

import com.devs.kero.team7.data.dataModel.TaskData;
import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.entities.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class TaskMapper implements  BaseMapper<TaskData, Task> {

    @Inject
    public TaskMapper() {
    }

    @Override
    public TaskData to(Task task) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String Date = dateFormat.format(task.getDateTime());
        String EndDate = dateFormat.format(task.getEndDate());
        return new TaskData(task.getTaskId(), task.getTaskTitle(), task.getTaskDescripion(),CategorieToInt.from(task.getCategorie()) , task.isActive(), Date,
                task.getHasRepeat(),task.getRepeatType(), task.getRepeatBody(), EndDate, task.getAdvancedRemind());
    }

    @Override
    public Task from(TaskData taskData) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date  date =  dateFormat.parse(taskData.getDate());

        Date endate  = dateFormat.parse(taskData.getDateEnd());

        return new Task(taskData.getId(), taskData.getTitle(), taskData.getDescription(),CategorieToInt.to(taskData.getCategorieId()) ,
                !taskData.isActive(),date, taskData.isHasRepeat(), taskData.getRepeatType(), taskData.getRepeatBody(),
                endate, taskData.getAdvancedReminder()
                );
    }
    public TaskData mapToAdd(Task task){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String Date = dateFormat.format(task.getDateTime());
        String EndDate = dateFormat.format(task.getEndDate());
        return new TaskData(task.getTaskTitle(), task.getTaskDescripion(),CategorieToInt.from(task.getCategorie()) , task.isActive(), Date,
                task.getHasRepeat(),task.getRepeatType(), task.getRepeatBody(), EndDate, task.getAdvancedRemind());
    }
}
