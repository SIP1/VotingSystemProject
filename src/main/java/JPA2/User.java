package JPA2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "User_TBL")
public class User implements Serializable
{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "username")
    //@Column(unique = true)
    private String username;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<ProposedSubject> proposedSubjects = new ArrayList();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserType_TBL_ID")
    private UserType userType = new UserType();
    //, unique= true, nullable=true, insertable=true, updatable=true

    @OneToMany(mappedBy = "user")
    private List<Vote> votes;

    private String password;
    private String name;
    private String email;

    public User()
    {
        // default constructor
    }

    public User(String username, String password, String name, String email)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public List<ProposedSubject> getProposedSubjects()
    {
        return proposedSubjects;
    }

    public void setProposedSubjects(List<ProposedSubject> proposedSubjects)
    {
        this.proposedSubjects = proposedSubjects;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }

    public List<Vote> getVotes()
    {
        return votes;
    }

    public void setVotes(List<Vote> votes)
    {
        this.votes = votes;
    }

    //Needed for testing
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    //Needed for testing
    @Override
    public String toString()
    {
        return "User{" + "username=" + username + ", proposedSubjects="
                + proposedSubjects + ", userType=" + userType + ", votes="
                + votes + ", password=" + password + ", name="
                + name + ", email=" + email + '}';
    }

}
