package util;

public class UtilMethods {

    public static String createJsonMessage(String message) {
        return "{"
                + "\"" + "Message" + "\"" + ":" + "\"" + message + "\""
                + "}";
    }

    public static String createJsonErrorMessage(String errorMessage) {
        return "{"
                + "\"" + "Error Message" + "\"" + ":" + "\"" + errorMessage + "\""
                + "}";
    }

}
