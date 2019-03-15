package com.eszter.lab11.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Students.class , version = 1)
public abstract class StudentsDatabase extends RoomDatabase {
    public abstract StudentsDao studentsDao();
    public static StudentsDatabase INSTANCE;

    public static StudentsDatabase getINSTANCE(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context , StudentsDatabase.class , "StudentsDB").build();
        }
        return INSTANCE;
    }

}
