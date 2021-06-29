public class StrUtil extends CharSequenceUtil implements StrPool{
    public static void trim(String[] strs) {
        if (strs == null) {
            return;
        }
        String str;
        for (int i = 0; i < strs.length; i++) {
            str = strs[i];
            if (str != null) {
                strs[i] = trim(str);
            }
        }
    }
}
