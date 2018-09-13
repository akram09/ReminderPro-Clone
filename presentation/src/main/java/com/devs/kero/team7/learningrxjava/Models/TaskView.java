package com.devs.kero.team7.learningrxjava.Models;

import com.devs.kero.team7.domain.entities.Categorie;

import java.util.Date;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class TaskView {
    @NonNull
    private long TaskId ;
    @NonNull
    private String TaskTitle ;
    @NonNull
    private String TaskDescripion ;

    private ColorView categorie;

    @NonNull
    private boolean isComplete ;
    @NonNull
    private Date dateTime ;

    @NonNull
    private boolean HasRepeat;
    @Nullable
    private String RepeatType ;
    @Nullable
    private String RepeatBody ;

    @Nullable
    private Date EndDate ;
    @NonNull
    private String AdvancedRemind ;

    public TaskView(long taskId, String taskTitle, String taskDescripion, ColorView categorie, boolean isComplete, Date dateTime, boolean hasRepeat, String repeatType, String repeatBody, Date endDate, String advancedRemind) {
        TaskId = taskId;
        TaskTitle = taskTitle;
        TaskDescripion = taskDescripion;
        this.categorie = categorie;
        this.isComplete = isComplete;
        this.dateTime = dateTime;
        HasRepeat = hasRepeat;
        RepeatType = repeatType;
        RepeatBody = repeatBody;
        EndDate = endDate;
        AdvancedRemind = advancedRemind;
    }

    public TaskView(String taskTitle, String taskDescripion, ColorView categorie, boolean isComplete, Date dateTime, boolean hasRepeat, String repeatType, String repeatBody, Date endDate, String advancedRemind) {
        TaskTitle = taskTitle;
        TaskDescripion = taskDescripion;
        this.categorie = categorie;
        this.isComplete = isComplete;
        this.dateTime = dateTime;
        HasRepeat = hasRepeat;
        RepeatType = repeatType;
        RepeatBody = repeatBody;
        EndDate = endDate;
        AdvancedRemind = advancedRemind;
    }

    public TaskView() {

    }

    public long getTaskId() {
        return TaskId;
    }

    public void setTaskId(long taskId) {
        TaskId = taskId;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public String getTaskDescripion() {
        return TaskDescripion;
    }

    public void setTaskDescripion(String taskDescripion) {
        TaskDescripion = taskDescripion;
    }

    public ColorView getCategorie() {
        return categorie;
    }

    public void setCategorie(ColorView categorie) {
        this.categorie = categorie;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isHasRepeat() {
        return HasRepeat;
    }

    public void setHasRepeat(boolean hasRepeat) {
        HasRepeat = hasRepeat;
    }

    public String getRepeatType() {
        return RepeatType;
    }

    public void setRepeatType(String repeatType) {
        RepeatType = repeatType;
    }

    public String getRepeatBody() {
        return RepeatBody;
    }

    public void setRepeatBody(String repeatBody) {
        RepeatBody = repeatBody;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getAdvancedRemind() {
        return AdvancedRemind;
    }

    public void setAdvancedRemind(String advancedRemind) {
        AdvancedRemind = advancedRemind;
    }
}
