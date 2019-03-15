package com.eszter.lab11.Room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class StudentsViewModal extends AndroidViewModel {
    StudentsRepository  studentsRepository;
    LiveData<List<Students>> list;

    public StudentsViewModal(@NonNull Application application) {
        super(application);
        studentsRepository = new StudentsRepository(application);
        list = studentsRepository.getAllStudents();
    }

    public void insert(Students students){
        studentsRepository.insert(students);
    }

    public void delete(Students students){
        studentsRepository.delete(students);
    }

    public void update(Students students){
        studentsRepository.update(students);
    }

    public LiveData<List<Students>> getAllStudents(){
        return  studentsRepository.getAllStudents();
    }
}
