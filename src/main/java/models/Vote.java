package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Vote_TBL")
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "votesSequenceGen")
    @SequenceGenerator(name = "votesSequenceGen", sequenceName = "votesSequence",
            initialValue = 1000, allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_USERNAME")
    private User user;

    @ManyToOne
    @JoinColumn
    private ProposedSubject proposedSubject;

    private Integer roundNumber;
    private Integer points;

    public Vote() {
        // default constructor
    }

    public Vote(User user, ProposedSubject proposedSubject, Integer roundNumber, Integer points) {
        this.user = user;
        this.proposedSubject = proposedSubject;
        this.roundNumber = roundNumber;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    //Needed for testing
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProposedSubject getProposedSubject() {
        return proposedSubject;
    }

    public void setProposedSubject(ProposedSubject proposedSubject) {
        this.proposedSubject = proposedSubject;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    //Needed for testing
    @Override
    public String toString() {
        return "Vote{" + "id=" + id + ", user=" + user + ", proposedSubject="
                + proposedSubject + ", roundNumber=" + roundNumber
                + ", points=" + points + '}';
    }

}
