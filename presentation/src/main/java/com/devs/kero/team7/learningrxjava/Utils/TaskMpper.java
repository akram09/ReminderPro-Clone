package com.devs.kero.team7.learningrxjava.Utils;

import android.graphics.Color;

import com.devs.kero.team7.data.dataModel.TaskData;
import com.devs.kero.team7.data.mapper.BaseMapper;
import com.devs.kero.team7.domain.entities.Categorie;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.learningrxjava.Models.ColorView;
import com.devs.kero.team7.learningrxjava.Models.TaskView;

import java.text.ParseException;

import javax.inject.Inject;

public class TaskMpper implements BaseMapper<TaskView, Task> {
    @Inject
    public TaskMpper() {
    }

    @Override
    public TaskView to(Task task) {
        ColorView colorView ;
        switch (task.getCategorie()){
            case Red: colorView = ColorView.Red;
            break;
            case Green:colorView = ColorView.Green ;
            break;
            case Yellow: colorView = ColorView.Yellow;
            break;
            case Defeult: colorView = ColorView.Default;
            break;
            case Blue:colorView = ColorView.Blue ;
            break;
            default:colorView = ColorView.Default;
        }
        return new TaskView(task.getTaskId(), task.getTaskTitle(), task.getTaskDescripion(), colorView,task.getComplete(), task
        .getDateTime(), task.getHasRepeat(), task.getRepeatType(), task.getRepeatBody(), task.getEndDate(), task.getAdvancedRemind());
    }

    @Override
    public Task from(TaskView taskView) throws ParseException {
        Categorie colorView ;
        switch (taskView.getCategorie()){
            case Red: colorView = Categorie.Red;
                break;
            case Green:colorView = Categorie.Green ;
                break;
            case Yellow: colorView = Categorie.Yellow;
                break;
            case Default: colorView = Categorie.Defeult;
                break;
            case Blue:colorView = Categorie.Blue ;
                break;
            default:colorView = Categorie.Defeult;
        }
        return new Task( taskView.getTaskId(),taskView.getTaskTitle(), taskView.getTaskDescripion(),colorView, taskView.isComplete(), taskView.getDateTime(),
                taskView.isHasRepeat(), taskView.getRepeatType(), taskView.getRepeatBody(),taskView.getEndDate(), taskView.getAdvancedRemind());
    }
    public Task fromToAdd(TaskView taskView){
        Categorie colorView ;
        switch (taskView.getCategorie()){
            case Red: colorView = Categorie.Red;
                break;
            case Green:colorView = Categorie.Green ;
                break;
            case Yellow: colorView = Categorie.Yellow;
                break;
            case Default: colorView = Categorie.Defeult;
                break;
            case Blue:colorView = Categorie.Blue ;
                break;
            default:colorView = Categorie.Defeult;
        }
        return new Task(taskView.getTaskTitle(), taskView.getTaskDescripion(),colorView, taskView.isComplete(), taskView.getDateTime(),
                taskView.isHasRepeat(), taskView.getRepeatType(), taskView.getRepeatBody(),taskView.getEndDate(), taskView.getAdvancedRemind());

    }
}
