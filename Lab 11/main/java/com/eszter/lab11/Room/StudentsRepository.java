package com.eszter.lab11.Room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class StudentsRepository {
    StudentsDao dao;
    LiveData<List<Students>> list;

    public StudentsRepository(Application application) {
        StudentsDatabase studentsDatabase = StudentsDatabase.getINSTANCE(application);
        this.dao = studentsDatabase.studentsDao();
        list = dao.getAllStudents();
    }

    public void insert(Students students){
        new Insert_Item(dao).execute(students);
    }

    public LiveData<List<Students>> getAllStudents(){
        return list;
    }

    private static class Insert_Item extends AsyncTask<Students , Void, Void>{
        StudentsDao dao;

        public Insert_Item(StudentsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Students... students) {
            dao.insert(students[0]);
            return null;
        }
    }

    public void delete(Students students){
        new Delete_Item(dao).execute(students);
    }

    private static class Delete_Item extends AsyncTask<Students , Void, Void>{
        StudentsDao dao;

        public Delete_Item(StudentsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Students... students) {
            dao.delete(students[0]);
            return null;
        }
    }

    public void update(Students students){
        new Update_Item(dao).execute(students);
    }

    private static class Update_Item extends AsyncTask<Students , Void, Void>{
        StudentsDao dao;

        public Update_Item(StudentsDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Students... students) {
            dao.update(students[0]);
            return null;
        }
    }
}
