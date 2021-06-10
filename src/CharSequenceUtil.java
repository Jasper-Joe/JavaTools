public class CharSequenceUtil {
    public static boolean isBlank(CharSequence str) {
        int length;
        if ((str == null) || (length = str.length()) == 0) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!CharUtil.isBlankChar(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
