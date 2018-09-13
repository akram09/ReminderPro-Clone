package com.devs.kero.team7.data.Db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.devs.kero.team7.data.dataModel.TaskData;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface TaskDao {
    @Query("SELECT *FROM TASK")
    Flowable<List<TaskData>> getAllTasks();
    @Query("SELECT * FROM Task WHERE isActive =1 ")
    Flowable<List<TaskData>> getActiveTaks();
    @Query("SELECT *FROM TASK WHERE isActive = 0")
    Flowable<List<TaskData>> getCompleteTasks() ;
    @Query("SELECT * FROM TASK WHERE Id= :id")
    Flowable<TaskData> getTaskById(long id) ;

    @Query("SELECT *FROM TASK WHERE Title = :string Or Description = :string")
    Flowable<List<TaskData>> search(String string) ;
    @Query("SELECT *FROM TASK WHERE isActive = 1 and (Title = :string or Description = :string)")
    Flowable<List<TaskData>> searchActive(String string ) ;

    @Query("SELECT *FROM TASK WHERE isActive = 0 and (Title = :string or Description = :string)")
    Flowable<List<TaskData>> searchComplete(String string ) ;

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskData taskData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(TaskData taskData);
    @Delete
    void deleteTask(TaskData task);
    @Delete
    void deleteTasks(List<TaskData> tasks) ;
    @Query("Delete from Task")
    void deleteAllTasks();
    @Query("Delete  from Task WHERE isActive = 1")
    void deletActiveTask() ;
    @Query("Delete  from Task WHERE isActive = 0")
    void deletCmpleteTask() ;
}
