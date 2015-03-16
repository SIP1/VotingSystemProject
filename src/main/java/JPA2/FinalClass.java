/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author smarandadungeanu
 */
@Entity
public class FinalClass implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
    
    List<User> students = new ArrayList();
    User teacher;
    ProposedSubject subject;

    public FinalClass(ProposedSubject subject)
    {
        this.subject = subject;
    }

    public List<User> getStudents()
    {
        return students;
    }

    public ProposedSubject getSubject()
    {
        return subject;
    }

    public User getTeacher()
    {
        return teacher;
    }

    public void setStudents(List<User> students)
    {
        this.students = students;
    }

    public void setSubject(ProposedSubject subject)
    {
        this.subject = subject;
    }

    public void setTeacher(User teacher)
    {
        this.teacher = teacher;
    }
    
}
