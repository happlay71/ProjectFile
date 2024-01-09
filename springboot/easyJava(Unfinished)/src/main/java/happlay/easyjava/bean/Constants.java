package happlay.easyjava.bean;

import happlay.easyjava.utils.PropertiesUtils;

public class Constants {
    public static Boolean IGNORE_TABLE_PREFIX;

    public static String SUFFIX_BEAN_PARAM;
    static {
        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtils.getString("prefix"));
        SUFFIX_BEAN_PARAM = PropertiesUtils.getString("param");
    }

}
