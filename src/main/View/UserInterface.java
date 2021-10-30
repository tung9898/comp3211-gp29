package View;

public class UserInterface {
    static String nl = "\n";
    static String tab = "\t";
    static String space = " ";
    static String colon = ":";
    static String to = "-";

    public static GameStatusView gsv = new GameStatusView();
    public static SystemMessageView sysv = new SystemMessageView();
    public static UserInputMessageView uimv = new UserInputMessageView();
    public static AlertMessageView amv = new AlertMessageView();
    public static SystemErrorMessageView sysev = new SystemErrorMessageView();
    public static SquareEffectMessageView semv = new SquareEffectMessageView();
}
