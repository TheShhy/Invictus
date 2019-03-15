package com.eszter.lab11.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface StudentsDao {

    @Query("SELECT * FROM students")
    public LiveData<List<Students>> getAllStudents();

    @Insert
    public void insert(Students students);

    @Delete
    public void delete(Students students);

    @Update
    public void update(Students students);

}
