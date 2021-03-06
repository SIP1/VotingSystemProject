package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FinalClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;

    public FinalClass() {
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    List<User> students = new ArrayList();
    List<User> teachers = new ArrayList();
    ProposedSubject subject;
    String name;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public FinalClass( ProposedSubject subject ) {
        this.subject = subject;
    }

    public List<User> getStudents() {
        return students;
    }

    public ProposedSubject getSubject() {
        return subject;
    }

    public List<User> getTeachers() {
        return teachers;
    }

    public String getTeachersToString() {
        String result = "";
        for ( User t : teachers ) {
            result = result + t.getName() + ", ";
        }
        if ( teachers.isEmpty() ) {
            return "";
        }
        return result.substring( 0, result.length() - 2 );
    }

    public void setStudents( List<User> students ) {
        this.students = students;
    }

    public void setSubject( ProposedSubject subject ) {
        this.subject = subject;
    }

    public void setTeachers( List<User> teachers ) {
        this.teachers = teachers;
    }
}
