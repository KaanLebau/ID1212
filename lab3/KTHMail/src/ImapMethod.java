public enum ImapMethod {
    LOGINCOMMAND("LOGIN"),
    LOGOUTCOMMAND("LOGOUT"),
    FETCHCOMMAND("FETCH"),
    SELECTCOMMAND("SELECT"),
    INBOXCOMMAND("INBOX"),
    MAILCOMMAND("body[text]");

    public static final String LOGIN = LOGINCOMMAND.getCommand();
    public static final String LOGOUT = LOGOUTCOMMAND.getCommand();
    public static final String FETCH = FETCHCOMMAND.getCommand();
    public static final String SELECT = SELECTCOMMAND.getCommand();
    public static final String INBOX = INBOXCOMMAND.getCommand();
    public static final String MAIL = MAILCOMMAND.getCommand();
    private final String command;
    ImapMethod(String command){
        this.command = command;
    }
    public String getCommand(){
        return command;
    }
}
