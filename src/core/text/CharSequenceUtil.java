package core.text;

import core.util.ArrayUtil;
import core.util.CharUtil;

import java.util.ArrayList;
import java.util.List;

public class CharSequenceUtil {

    public static final int INDEX_NOT_FOUND = -1;

    public static final String EMPTY = "";
    public static final String NULL = "null";

    public static boolean endWith(CharSequence str, char c) {
        if (isEmpty(str)) {
            return false;
        }
        return str.charAt(str.length() - 1) == c;
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static List<String> split(CharSequence str, char separator) {
        return split(str, separator, 0);
    }

    public static List<String> split(CharSequence str, char separator, int limit) {
        return split(str, separator, limit, false, false);
    }

    public static List<String> split(CharSequence str, char separator, int limit, boolean isTrim, boolean ignoreEmpty) {
        if (str == null) {
            return new ArrayList<>();
        }
        return StrSplitter.split(str.toString(), separator, limit, isTrim, ignoreEmpty);
    }

    public static String getContainsStr(CharSequence str, CharSequence... testStrs) {
        if (isEmpty(str) || ArrayUtil.isEmpty(testStrs)) {
            return null;
        }

        for (CharSequence checkStr : testStrs) {
            if (str.toString().contains(checkStr)) {
                return checkStr.toString();
            }
        }
        return null;
    }

    public static String sub(CharSequence str, int fromIndexInclude, int toIndexExclude) {
        if (isEmpty(str)) {
            return str(str);
        }
        int len = str.length();
        if (fromIndexInclude < 0) {
            fromIndexInclude = len + fromIndexInclude;
            if (fromIndexInclude < 0) {
                fromIndexInclude = 0;
            }
        } else if (fromIndexInclude > len) {
            fromIndexInclude = len;
        }

        if (toIndexExclude < 0) {
            toIndexExclude = len + toIndexExclude;
            if (toIndexExclude < 0) {
                toIndexExclude = len;
            }
        } else if (toIndexExclude > len) {
            toIndexExclude = len;
        }

        if (toIndexExclude < fromIndexInclude) {
            int tmp = fromIndexInclude;
            fromIndexInclude = toIndexExclude;
            toIndexExclude = tmp;
        }

        if (fromIndexInclude == toIndexExclude) {
            return EMPTY;
        }

        return str.toString().substring(fromIndexInclude, toIndexExclude);
    }

    public static String subSuf(CharSequence string, int fromIndex) {
        if (isEmpty(string)) {
            return null;
        }
        return sub(string, fromIndex, string.length());
    }

    public static String subPre(CharSequence string, int toIndexExclude) {
        return sub(string, 0, toIndexExclude);
    }

    public static String removeSuffix(CharSequence str, CharSequence suffix) {
        if (isEmpty(str) || isEmpty(suffix)) {
            return str(str);
        }
        final String str2 = str.toString();
        if (str2.endsWith(suffix.toString())) {
            return subPre(str2, str2.length() - suffix.length());
        }
        return str2;
    }

    public static String removePrefix(CharSequence str, CharSequence prefix) {
        if (isEmpty(str) || isEmpty(prefix)) {
            return str(str);
        }
        final String str2 = str.toString();
        if (str2.startsWith(prefix.toString())) {
            return subSuf(str2, prefix.length());
        }
        return str2;
    }

    public static boolean containsAny(CharSequence str, CharSequence... testStrs) {
        return getContainsStr(str, testStrs) != null;
    }

    public static boolean containsAny(CharSequence str, char... testChars) {
        if (!isEmpty(str)) {
            int len = str.length();
            for (int i = 0; i < len; i++) {
                if (ArrayUtil.contains(str.charAt(i), testChars)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsAny(CharSequence str, CharSequence testStrs) {
        return getContainsStr(str, testStrs) != null;
    }

    public static String format(CharSequence template, Object... params) {
        if (template == null) {
            return NULL;
        }
        if (ArrayUtil.isEmpty(params) || isBlank(template)) {
            return template.toString();
        }
        return StrFormatter.format(template.toString(), params);
    }

    public static String addPrefixIfNot(CharSequence str, CharSequence prefix) {
        return prependIfMissing(str, prefix, prefix);
    }

    public static String prependIfMissing(CharSequence str, CharSequence prefix, CharSequence... prefixes) {
        return prependIfMissing(str, prefix, false, prefixes);
    }

    public static String prependIfMissingIgnoreCase(CharSequence str, CharSequence prefix, CharSequence... prefixes) {
        return prependIfMissing(str, prefix, true, prefixes);
    }

    public static String prependIfMissing(CharSequence str, CharSequence prefix, boolean ignoreCase, CharSequence... prefixes) {
        if (str == null || isEmpty(prefix) || startWith(str, prefix, ignoreCase)) {
            return str(str);
        }
        if (prefixes != null && prefixes.length > 0) {
            for (final CharSequence s : prefixes) {
                if (startWith(str, s, ignoreCase)) {
                    return str.toString();
                }
            }
        }
        return prefix.toString().concat(str.toString());
    }

    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    public static boolean startWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return startWith(str, prefix, true);
    }

    public static boolean startWith(CharSequence str, char c) {
        if (isEmpty(str)) {
            return false;
        }
        return c == str.charAt(0);
    }

    public static boolean startWith(CharSequence str, CharSequence prefix, boolean ignoreCase) {
        return startWith(str, prefix, ignoreCase, false);
    }

    public static boolean startWith(CharSequence str, CharSequence prefix, boolean ignoreCase, boolean ignoreEquals) {
        if (str == null || prefix == null) {
            if (ignoreEquals == false) {
                return false;
            }
            return str == null && prefix == null;
        }

        boolean isStartWith;
        if (ignoreCase) {
            isStartWith = str.toString().toLowerCase().startsWith(prefix.toString().toLowerCase());
        } else {
            isStartWith = str.toString().startsWith(prefix.toString());
        }
        if (isStartWith) {
            return (ignoreEquals == false) || (equals(str, prefix, ignoreCase) == false);
        }

        return false;
    }

    public static boolean equals(CharSequence str1, CharSequence str2) {
        return equals(str1, str2, false);
    }


    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (str1 == null) {
            return str2 == null;
        }
        if (str2 == null) {
            return false;
        }

        if (ignoreCase) {
            return str1.toString().equalsIgnoreCase(str2.toString());
        } else {
            return str1.toString().contentEquals(str2);
        }
    }

    public static int indexOf(final CharSequence str, char searchChar) {
        return indexOf(str, searchChar, 0);
    }


    public static String nullToEmpty(CharSequence str) {
        return nullToDefault(str, EMPTY);
    }

    public static String nullToDefault(CharSequence str, String defaultStr) {
        return (str == null) ? defaultStr : str.toString();
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

    public static int indexOf(final CharSequence str, CharSequence searchStr, int fromIndex, boolean ignoreCase) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        final int endLimit = str.length() - searchStr.length() + 1;
        if (fromIndex > endLimit) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return fromIndex;
        }
        if (ignoreCase == false) {
            return str.toString().indexOf(searchStr.toString(), fromIndex);
        }

        for (int i = fromIndex; i < endLimit; i++) {
            if (isSubEquals(str, i, searchStr, 0, searchStr.length(), true)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static boolean isSubEquals(CharSequence str1, int start1, CharSequence str2, int start2, int length, boolean ignoreCase) {
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.toString().regionMatches(ignoreCase, start1, str2.toString(), start2, length);
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

    public static String replace(CharSequence str, CharSequence searchStr, CharSequence replacement) {
        return replace(str, 0, searchStr, replacement, false);
    }

    public static String replace(CharSequence str, CharSequence searchStr, CharSequence replacement, boolean ignoreCase) {
        return replace(str, 0, searchStr, replacement, ignoreCase);
    }



    public static String replace(CharSequence str, int fromIndex, CharSequence searchStr, CharSequence replacement, boolean ignoreCase) {
        if (isEmpty(str) || isEmpty(searchStr)) {
            return str(str);
        }
        if (replacement == null) {
            replacement = EMPTY;
        }

        final int strLength = str.length();
        final int searchStrLength = searchStr.length();
        if (fromIndex > strLength) {
            return str(str);
        } else if (fromIndex < 0) {
            fromIndex = 0;
        }

        final StrBuilder result = StrBuilder.create(strLength + 16);

        if (fromIndex != 0) {
            result.append(str.subSequence(0, fromIndex));
        }
        int preIndex = fromIndex;
        int index;
        while ((index = indexOf(str, searchStr, preIndex, ignoreCase)) > -1) {
            result.append(str.subSequence(preIndex, index));
            result.append(replacement);
            preIndex = index + searchStrLength;
        }
        if (preIndex < strLength) {
            result.append(str.subSequence(preIndex, strLength));
        }
        return result.toString();

    }

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
