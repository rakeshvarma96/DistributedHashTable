package util;

import java.util.Objects;

public class ValidationUtil {

    public static void validateNonNull(Object key, String param) throws Exception {
        if(Objects.isNull(key))
            throw new Exception(param + " can't be null.");
    }

    public static void validateNonEmptyString(String s, String param) throws Exception {
        if(s.isBlank())
            throw new Exception(param + " can't be empty.");
    }
}
