package happlay.utils;

import happlay.entity.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtils {
    public static boolean verify(String regex, String value) {
        if (StringTools.isEmpty(value)) {
            return false;
        }
        // 使用 Java 的正则表达式功能
        // 该对象表示编译后的正则表达式，用于后续的匹配.regex 是一个表示正则表达式的字符串，这个字符串定义了匹配的模式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    // 枚举
    public static boolean verify(VerifyRegexEnum regexEnum, String value) {
        return verify(regexEnum.getRegex(), value);
    }
}
