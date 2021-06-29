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

    public static String trim(CharSequence str) {
        return (str == null) ? null : trim(str, 0);
    }

    public static String trimEnd(CharSequence str) {
        return trim(str, 1);
    }

    /**
     *
     * @param str
     * @param mode {-1} means trimStart, {0} means trim all, {1} means trimEnd
     * @return
     */
    public static String trim(CharSequence str, int mode) {
        String result;
        if (str == null) {
            result = null;
        } else {
            int length = str.length();
            int start = 0;
            int end = length;
            if (mode <= 0) {
                while ((start < end) && (CharUtil.isBlankChar(str.charAt(start)))) {
                    start++;
                }
            }
            if (mode >= 0) {
                while ((start < end) && (CharUtil.isBlankChar(str.charAt(end - 1)))) {
                    end--;
                }
            }

            if ((start > 0) || (end < length)) {
                result = str.toString().substring(start, end);
            } else {
                result = str.toString();
            }
        }
        return result;
    }




























}
