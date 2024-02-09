package proj.utils;

import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static boolean validateName(String name) {
        return name != null && name.length() >= 2 && name.length() <= 50;
    }

    public static boolean validateEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean validateLogin(String login) {
        return login != null && login.length() >= 3 && login.length() <= 30;
    }

    public static boolean validatePassword(String password) {
        return password != null && password.length() >= 8 && password.length() <= 100;
    }

    public static boolean validateSelfInfo(String selfInfo) {
        return selfInfo == null || selfInfo.length() <= 500;
    }

    public static boolean validateUser(String name, String email, String login, String password, String selfInfo) {
        return validateName(name) && validateEmail(email) && validateLogin(login) && validatePassword(password) && validateSelfInfo(selfInfo);
    }
}
