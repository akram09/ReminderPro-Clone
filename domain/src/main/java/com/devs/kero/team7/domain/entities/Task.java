package com.devs.kero.team7.domain.entities;

import java.util.Date;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class Task {
    @NonNull
    private long TaskId ;
    @NonNull
    private String TaskTitle ;
     @NonNull
    private String TaskDescripion ;

    private Categorie categorie;

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

    public Task(String taskTitle, String taskDescripion, Categorie categorie, boolean isComplete, Date dateTime, boolean hasRepeat, String repeatType, String repeatBody, Date endDate, String advancedRemind) {
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

    public Task() {
    }

    public Task(long taskId, String taskTitle, String taskDescripion,Categorie categorie, boolean isComplete, Date dateTime, boolean hasRepeat, String repeatType, String repeatBody, Date endDate, String advancedRemind) {
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public boolean getComplete() {
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

    public boolean getHasRepeat() {
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
    public boolean isActive(){
        return !this.isComplete;    }
        public void setComplete(){
        isComplete =true;
        }

}
