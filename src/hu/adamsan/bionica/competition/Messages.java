package hu.adamsan.bionica.competition;

import java.util.ResourceBundle;

public class Messages {


    public static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public static final String START_HEADER_MESSAGE = "" +
            "  ____  _       _    _                        _  __      ___                                       _                    \r\n" +
            " |  _ \\(_)     | |  | |                      | | \\ \\    / (_)                    /\\               | |                   \r\n" +
            " | |_) |_  ___ | |__| | __ _ ______ _ _ __ __| |  \\ \\  / / _ _ __ _   _ ___     /  \\   _ __   __ _| |_   _ _______ _ __ \r\n" +
            " |  _ <| |/ _ \\|  __  |/ _` |_  / _` | '__/ _` |   \\ \\/ / | | '__| | | / __|   / /\\ \\ | '_ \\ / _` | | | | |_  / _ \\ '__|\r\n" +
            " | |_) | | (_) | |  | | (_| |/ / (_| | | | (_| |    \\  /  | | |  | |_| \\__ \\  / ____ \\| | | | (_| | | |_| |/ /  __/ |   \r\n" +
            " |____/|_|\\___/|_|  |_|\\__,_/___\\__,_|_|  \\__,_|     \\/   |_|_|   \\__,_|___/ /_/    \\_\\_| |_|\\__,_|_|\\__, /___\\___|_|   \r\n" +
            "                                                                                                      __/ |             \r\n" +
            "                                                                                                     |___/              \r\n" +
            "";
    public static final String VERSION_INFO = bundle.getString("version");
    public static final String REQUEST_TEAM_INFO = bundle.getString("REQUEST_TEAM_INFO");
    public static final String CONFIRM_TEAM_NAME = bundle.getString("CONFIRM_TEAM_NAME");
    public static final String CONFIRM_ANSWER = bundle.getString("CONFIRM_ANSWER");
    public static final String QUESTIONS_START = bundle.getString("QUESTIONS_START");
    public static final String ENDING_MESSAGE = bundle.getString("ENDING_MESSAGE");
    public static final String LINE = bundle.getString("LINE");
    public static final String END_SCORE_MESSAGE = bundle.getString("END_SCORE_MESSAGE");

}
