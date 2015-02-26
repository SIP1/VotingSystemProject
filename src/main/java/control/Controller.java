package control;


import java.util.ArrayList;
import utilities.AcceptanceProtocol;

public class Controller {
    
    public ArrayList subjects;
    
    public static void main(String[] args) {
        new Controller().mainer();
    }

    public Controller() {
        mainer();
//        AcceptanceProtocol ac
    }

    private void mainer() {
        authenticate("bobkoo", "12345");
        subjects = new ArrayList();
        addSubject(new ProposedElectiveSubjects("AI", "make it think", Boolean.TRUE));
        addSubject(new ProposedElectiveSubjects("C#", "java like", Boolean.TRUE));
        addSubject(new ProposedElectiveSubjects("C++", "complicated", Boolean.FALSE));
        addSubject(new ProposedElectiveSubjects("Game Design", "WOW", Boolean.TRUE));
        getAllAvailableSubjects();
    }
    
    public String authenticate(String userName, String password) {
        return "Authentication successfull";
    }
    
    public ArrayList getAllAvailableSubjects() {
       return subjects;
    }
    
    public ProposedElectiveSubjects addSubject(ProposedElectiveSubjects pes) {
        subjects.add(pes);
        return pes;
    }
    
    public String vote(ArrayList<ProposedElectiveSubjects> alPES, User user){
        return AcceptanceProtocol.SUCCESS;
    }
    
    
    //Nested class representing the ProposedSubject table (until it's created)
    private Integer pesIDCreator = 0;
    public class ProposedElectiveSubjects {
        private Integer id;
        private String name, description, poolOptions;
        private Boolean isAlive;

        public ProposedElectiveSubjects(String name, String description, Boolean isAlive) {
            this.id = pesIDCreator++;
            this.name = name;
            this.description = description;
            this.isAlive = isAlive;
            this.poolOptions = "";
        }

        public Integer getId() {
            return id;
        }
        
        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setIsAlive(Boolean isAlive) {
            this.isAlive = isAlive;
        }

        public void setPoolOptions(String poolOptions) {
            this.poolOptions = poolOptions;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getPoolOptions() {
            return poolOptions;
        }

        public Boolean isIsAlive() {
            return isAlive;
        }
        
    }
    
    private Integer votesIDCreator = 0;
    class Votes {
        private Integer id, points;
        private String userNameStudent, proposedElectiveSubjectsID, roundNo;

        public Votes(String userNameStudent, String proposedElectiveSubjectsID, String roundNo, Integer points) {
            this.userNameStudent = userNameStudent;
            this.proposedElectiveSubjectsID = proposedElectiveSubjectsID;
            this.roundNo = roundNo;
            this.points = points;
            this.id = votesIDCreator++;
        }

        public Integer getPoints() {
            return points;
        }

        public String getProposedElectiveSubjectsID() {
            return proposedElectiveSubjectsID;
        }

        public String getRoundNo() {
            return roundNo;
        }

        public String getUserNameStudent() {
            return userNameStudent;
        }

        public void setUserNameStudent(String userNameStudent) {
            this.userNameStudent = userNameStudent;
        }

        public void setRoundNo(String roundNo) {
            this.roundNo = roundNo;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        public void setProposedElectiveSubjectsID(String proposedElectiveSubjectsID) {
            this.proposedElectiveSubjectsID = proposedElectiveSubjectsID;
        }
    }
    
    class User {
        private Integer useTypeID;
        private String userName, name ,password, email;

        public User(Integer useTypeID, String userName, String name, String password, String email) {
            this.useTypeID = useTypeID;
            this.userName = userName;
            this.name = name;
            this.password = password;
            this.email = email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUseTypeID(Integer useTypeID) {
            this.useTypeID = useTypeID;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

        public Integer getUseTypeID() {
            return useTypeID;
        }

        public String getUserName() {
            return userName;
        }
    }
    
}
