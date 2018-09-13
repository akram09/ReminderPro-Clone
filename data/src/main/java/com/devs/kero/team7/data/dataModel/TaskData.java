package com.devs.kero.team7.data.dataModel;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

@Entity(tableName = "Task")
public class TaskData {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long Id ;
    @Nullable
   private String Title ;

    @Nullable
    private String Description ;
    @NonNull
    private int CategorieId ;
     @NonNull
    private  boolean isActive ;
     @NonNull
     private String Date ;

     @NonNull
     private boolean HasRepeat ;

    @Nullable
    private String RepeatType ;
    @Nullable
    private String RepeatBody ;
    @NonNull
    private String DateEnd ;

    @NonNull
    private String AdvancedReminder ;

    public TaskData(String title, String description, int categorieId, boolean isActive, String date, boolean hasRepeat, String repeatType, String repeatBody, String dateEnd, String advancedReminder) {
        Title = title;
        Description = description;
        CategorieId = categorieId;
        this.isActive = isActive;
        Date = date;
        HasRepeat = hasRepeat;
        RepeatType = repeatType;
        RepeatBody = repeatBody;
        DateEnd = dateEnd;
        AdvancedReminder = advancedReminder;
    }

    public TaskData(long id, String title, String description, int categorieId, boolean isActive, String date, boolean hasRepeat, String repeatType, String repeatBody, String dateEnd, String advancedReminder) {
        Id = id;
        Title = title;
        Description = description;
        CategorieId = categorieId;
        this.isActive = isActive;
        Date = date;
        HasRepeat = hasRepeat;
        RepeatType = repeatType;
        RepeatBody = repeatBody;
        DateEnd = dateEnd;
        AdvancedReminder = advancedReminder;
    }

    public TaskData() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getCategorieId() {
        return CategorieId;
    }

    public void setCategorieId(int categorieId) {
        CategorieId = categorieId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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

    public String getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(String dateEnd) {
        DateEnd = dateEnd;
    }

    public String getAdvancedReminder() {
        return AdvancedReminder;
    }

    public void setAdvancedReminder(String advancedReminder) {
        AdvancedReminder = advancedReminder;
    }
}
