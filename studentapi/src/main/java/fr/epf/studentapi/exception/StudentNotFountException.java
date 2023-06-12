package fr.epf.studentapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Student with this id not exist")
public class StudentNotFountException extends RuntimeException {
    
}
