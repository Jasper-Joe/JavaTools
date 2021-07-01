package core.text;

import core.util.CharUtil;

public class CharSequenceUtil {

    public static final int INDEX_NOT_FOUND = -1;

    public static final String EMPTY = "";

    public static int indexOf(final CharSequence str, char searchChar) {
        return indexOf(str, searchChar, 0);
    }

    public static int indexOf(CharSequence str, char searchChar, int start) {
        if (str instanceof String) {
            return ((String) str).indexOf(searchChar, start);
        } else {
            return indexOf(str, searchChar, start, -1);
        }
    }

    public static int indexOf(final CharSequence str, char searchChar, int start, int end) {
        if (isEmpty(str)) {
            return INDEX_NOT_FOUND;

        }
        final int len = str.length();
        if (start < 0 || start > len) {
            start = 0;
        }
        if (end > len || end < 0) {
            end = len;
        }
        for (int i = start; i < end; i++) {
            if (str.charAt(i) == searchChar) {
                return i;
            }
        }

        return INDEX_NOT_FOUND;
    }

    public static boolean isBlank(CharSequence str) {
        int length;
        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!CharUtil.isBlankChar(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String str(CharSequence cs) {
        return cs == null ? null : cs.toString();
    }

//    public static String replace(CharSequence str, CharSequence searchStr, CharSequence replacement) {
//        return replace(str, 0, searchStr, replacement, false);
//    }
//
//    public static String replace(CharSequence str, CharSequence searchStr, CharSequence replacement, boolean ignoreCase) {
//        return replace(str, 0, searchStr, replacement, ignoreCase);
//    }



//    public static String replace(CharSequence str, int fromIndex, CharSequence searchStr, CharSequence replacement, boolean ignoreCase) {
//        if (isEmpty(str) || isEmpty(searchStr)) {
//            return str(str);
//        }
//        if (replacement == null) {
//            replacement = EMPTY;
//        }
//
//        final int strLength = str.length();
//        final int searchStrLength = searchStr.length();
//        if (fromIndex > strLength) {
//            return str(str);
//        } else if (fromIndex < 0) {
//            fromIndex = 0;
//        }
//
//        final StrBuilder result = StrBuilder.create(strLength + 16);
//
//        if (fromIndex != 0) {
//            result.append(str.subSequence(0, fromIndex));
//        }
//        int preIndex = fromIndex;
//        int index;
//        while ((index = indexOf(str, searchStr, preIndex, ignoreCase)) > -1) {
//            result.append(str.subSequence(preIndex, index));
//            result.append(replacement);
//            preIndex = index + searchStrLength;
//        }
//        if (preIndex < strLength) {
//            result.append(str.subSequence(preIndex, strLength));
//        }
//        return result.toString();
//
//    }

    public static String replace(CharSequence str, int startInclude, int endExclude, char replaceChar) {
        if (isEmpty(str)) {
            return str(str);
        }
        final int strLength = str.length();
        if (startInclude > strLength) {
            return str(str);
        }
        if (endExclude > strLength) {
            endExclude = strLength;
        }
        if (startInclude > endExclude) {
            return str(str);
        }

        final char[] chars = new char[strLength];
        for (int i = 0; i < strLength; i++) {
            if (i >= startInclude && i < endExclude) {
                chars[i] = replaceChar;
            } else {
                chars[i] = str.charAt(i);
            }
        }
        return new String(chars);
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() ==0;
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
