public enum SmtpMethod {
    HELOCOMMAND("HELO"),
    LOGINCOMMAND("AUTH LOGIN"),
    FROMCOMMAND("MAIL FROM"),
    RCPTCOMMAND("RCPT TO"),
    DATACOMMAND("DATA"),
    QUITCOMMAND("QUIT");

    public static final String HELO = HELOCOMMAND.getCommand();
    public static final String LOGIN = LOGINCOMMAND.getCommand();
    public static final String FROM = FROMCOMMAND.getCommand();
    public static final String RCPT = RCPTCOMMAND.getCommand();
    public static final String DATA = DATACOMMAND.getCommand();
    public static final String QUIT = QUITCOMMAND.getCommand();

    private final String command;
    SmtpMethod(String command){
        this.command = command;
    }
    public String getCommand(){
        return command;
    }
}
