package utils;

import java.text.SimpleDateFormat;

public class Constant {
	public static final String TEXT="1";
	public static final String IMAGE="2";
    public static final String IP="http://114.115.148.101/PosShare/";
    public static  String ImageDirectory;
    public static String returnTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }
}
