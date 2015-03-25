package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ProposedSubject_TBL")
public class ProposedSubject implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Boolean isAlive;
    private String poolOptions;

    @ManyToMany()
    private List<User> users = new ArrayList();

    public ProposedSubject()
    {

    }

    public ProposedSubject(String name, String description, Boolean isAlive, String poolOptions)
    {
        this.name = name;
        this.description = description;
        this.isAlive = isAlive;
        this.poolOptions = poolOptions;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean isItAlive()
    {
        return isAlive;
    }

    public void setIsAlive(Boolean isAlive)
    {
        this.isAlive = isAlive;
    }

    public String getPoolOptions()
    {
        return poolOptions;
    }

    public void setPoolOptions(String poolOptions)
    {
        this.poolOptions = poolOptions;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public String getTeachersNames()
    {
        String result = "";
        for (User u : users)
        {
            if (u.getUserType().getName().equals("Teacher"))
            {
                result = result + u.getName() + ", ";
            }
        }
        if (result.length() > 1)
        {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return "ProposedSubject{" + "id=" + id + ", name=" + name + ", description="
                + description + ", isAlive=" + isAlive + ", poolOptions=" + poolOptions
                + ", users=" + users + '}';
    }

}
