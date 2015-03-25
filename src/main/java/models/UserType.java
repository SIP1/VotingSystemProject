package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "UserType_TBL")
public class UserType implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userTypesSequenceGen")
    @SequenceGenerator(name = "userTypesSequenceGen", sequenceName = "userTypesSequence",
            initialValue = 1000, allocationSize = 1)
    private Integer id;
    private String name;

    public UserType()
    {

    }

    public UserType(String name)
    {
        this.name = name;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "UserType{" + "id=" + id + ", name=" + name + '}';
    }

}
