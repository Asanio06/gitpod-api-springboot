package fr.epf.studentapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epf.studentapi.dao.StudentDao;
import fr.epf.studentapi.exception.StudentNotFountException;
import fr.epf.studentapi.model.Student;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Student> getAll() {

        return studentDao.findAll();
    }

    public Student getById(Long id) {

        return studentDao.findById(id).orElseThrow(StudentNotFountException::new);
    }

    public Student create(Student student){
        long id = studentDao.create(student);

        return getById(id);
    }


    public Student update(Student student,Long id){
        studentDao.update(student,id);

        return getById(id);
    }

    public Student partialUpdate(Student student,Long id){

        Student existingStudent = getById(id);

        if(student.getEmail()!=null){
            existingStudent.setEmail(student.getEmail());
        }

        if(student.getFirstname()!=null){
            existingStudent.setFirstname(student.getFirstname());
        }

        if(student.getLastname()!=null){
            existingStudent.setLastname(student.getLastname());
        }

        if(student.getBirthDate()!=null){
            existingStudent.setBirthDate(student.getBirthDate());
        }

        studentDao.update(existingStudent,id);

        return getById(id);
    }

}
