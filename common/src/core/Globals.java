package core;

import console.commads.*;
import console.commads.generalCommands.Command;

import java.util.HashMap;
import java.util.Map;

public class Globals {
    public static byte ARGUMENT_POSITION = 1; // Position at split string
    public static byte COMMAND_POSITION = 0;
    public static int SERVER_PORT = 8000;
    public static byte HISTORY_SIZE = 8;
    public static short COORDINATE_X_MIN_LIMIT = -873;

    public static int DATA_SIZE = 507;
    public static int PACKET_SIZE = 508;

    static public class CommandNames {
        public static String EXECUTE_SCRIPT = "execute_script";
        public static String HELP = "help";
        public static String INFO = "info";
        public static String SHOW = "show";
        public static String ADD = "add";
        public static String UPDATE = "update";
        public static String REMOVE_BY_ID = "remove_by_id";
        public static String CLEAR = "clear";
        public static String EXIT = "exit";
        public static String ADD_IF_MAX = "add_if_max";
        public static String REMOVE_LOWER = "remove_lower";
        public static String HISTORY = "history";
        public static String GROUP_COUNTING_BY_CREATION_DATE = "group_counting_by_creation_date";
        public static String COUNT_GREATER_THAN_TYPE = "count_greater_than_type";
        public static String PRINT_FIELD_ASCENDING_TYPE = "print_field_ascending_type";
        public static String TICKET_EXIST = "ticket_exist";

        public static String LOGIN = "login";
        public static String REGISTER = "register";
        public static String LOGOUT = "logout";
        public static String LOGIN_USER = "login_user";

        public static Map<String, Class<? extends Command>> commandMap = new HashMap<>();
        static {
            commandMap.put(HELP, HelpCommand.class);
            commandMap.put(INFO, InfoCommand.class);
            commandMap.put(SHOW, ShowCommand.class);
            commandMap.put(ADD, AddCommand.class);
            commandMap.put(UPDATE, UpdateCommand.class);
            commandMap.put(REMOVE_BY_ID, RemoveCommand.class);
            commandMap.put(CLEAR, ClearCommand.class);
            commandMap.put(EXECUTE_SCRIPT, ExecuteScriptCommand.class);
            commandMap.put(ADD_IF_MAX, AddIfMaxCommand.class);
            commandMap.put(REMOVE_LOWER, RemoveLowerCommand.class);
            commandMap.put(EXIT, ExitCommand.class);
            commandMap.put(HISTORY, HistoryCommand.class);
            commandMap.put(GROUP_COUNTING_BY_CREATION_DATE, GroupByDateCommand.class);
            commandMap.put(COUNT_GREATER_THAN_TYPE, GreaterThenTypeCommand.class);
            commandMap.put(PRINT_FIELD_ASCENDING_TYPE, PrintTypeAscendingCommand.class);
            commandMap.put(LOGIN, LoginCommand.class);
            commandMap.put(REGISTER, RegisterCommand.class);
            commandMap.put(LOGOUT, LogoutCommand.class);
        }
    }

    static public class CoordinatesFields {
        public static String X = "x";
        public static String Y = "y";
    }

    static public class LocationFields {
        public static String X = "x";
        public static String Y = "y";
        public static String Z = "z";
    }

    static public class PersonFields {
        public static String BIRTHDAY = "birthday";
        public static String EYE_COLOR = "eyeColor";
        public static String HAIR_COLOR = "hairColor";
        public static String NATIONALITY = "nationality";
        public static String LOCATION = "location";
    }

    static public class Responses {
        public static byte TICKET_WAS_NOT_ADDED = -1;
    }

    static public class Network {
        public static byte PACKET_CONTINUES = 1;
        public static byte PACKET_ENDS = 0;
        public static byte TICKET_IS_NOT_EXIST = 0;
        public static byte TICKET_IS_EXIST = 1;

        public static String USERNAME = "username";
        public static String PASSWORD = "password";

        public static String IS_LOGGED = "is_logged";
    }
}
