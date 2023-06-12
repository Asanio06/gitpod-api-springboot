package fr.epf.studentapi.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.epf.studentapi.model.Student;

@Repository
public class StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String CREATE_STUDENT_QUERY = "INSERT INTO student(firstname, lastname, email, birthdate) VALUES(:firstname,:lastname,:email,:birthdate);";
	private static final String UPDATE_STUDENT_QUERY = "UPDATE student SET firstname = :firstname, lastname=:lastname, email=:email, birthdate=:birthdate where id=:id";
	private static final String FIND_ALL_STUDENT_QUERY = "SELECT id,firstname,lastname,email,birthdate FROM student";
	private static final String FIND_STUDENT_QUERY = "SELECT id,firstname,lastname,email,birthdate FROM student where id=:id";



    public List<Student> findAll(){

       List<Student> students =  jdbcTemplate.query(FIND_ALL_STUDENT_QUERY, new StudentMapper());
       return students;

    }

    public Optional<Student> findById(Long id){
        SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("id", id);
        Optional<Student> student =  Optional.ofNullable(jdbcTemplate.queryForObject(FIND_STUDENT_QUERY, parameters, new StudentMapper()));
        return student;
     }

    public long create(Student student){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("firstname", student.getFirstname())
        .addValue("lastname", student.getLastname())
        .addValue("birthdate", student.getBirthDate())
        .addValue("email", student.getEmail());

        jdbcTemplate.update(CREATE_STUDENT_QUERY,parameters,keyHolder, new String[] { "id" });

        return (long) keyHolder.getKey();
    }


    public void update(Student student,Long id){
        SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("id", id)
        .addValue("firstname", student.getFirstname())
        .addValue("lastname", student.getLastname())
        .addValue("birthdate", student.getBirthDate())
        .addValue("email", student.getEmail());

        jdbcTemplate.update(UPDATE_STUDENT_QUERY,parameters);
    }

    

}
