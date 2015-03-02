package utilities;

public class AcceptanceProtocol {

    public static final String ACCOUNT_LOGIN_SUCCESS = "Authentication successfull!";

    public static final String ACCOUNT_LOGIN_ERROR = "Your username or password "
            + "is incorrect.";

    public static final String ACCOUNT_UPDATE_SUCCESS = "Your account was updated "
            + "successfully";

    public static final String ACCOUNT_UPDATE_ERROR = "Account update error!";

    public static final String ACCOUNT_REGISTRATION_SUCCESS = "You registered successfully!";

    public static final String ACCOUNT_REGISTRATION_ERROR = "Username already taken";

    public static final String ACCOUNT_DELETION = "Your account was deleted";

    public static final String ACCOUNT_DELETION_ERROR = "You failed to delete your "
            + "account";
    
    public static final String SUBJECT_DELETION_SUCCESS = "The subject was deleted "
            + "successfully";

    public static final String VOTE_REGISTRATION_SUCCESS = "Your vote are registered "
            + "successfully!";

    public static final String VOTE_REGISTRATION_ERROR_AMMOUNT = "You have to select "
            + "exactly 4 (four) subjects.";

    public static final String VOTE_REGISTRATION_ERROR_REPETITION = "You have voted "
            + "twice or more for the same subject";

    public static final String VOTE_REGISTRATION_ERROR_ROUNDS = "You can only vote "
            + "for 1 Round at a time";

    public static final String VOTE_UPDATE_SUCCESS = "Your votes are updated successfully!";

    public static final String VOTE_DELETION_SUCCESS = "Your votes are deleted.";
    
    public static final String VOTE_DELETION_FAIL = "You failed to delete your votes";
}
