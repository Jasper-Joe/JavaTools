package core.text;

import core.util.ArrayUtil;
import core.util.StrUtil;

public class StrFormatter {

    public static String format(final String strPattern, final Object... argArray) {
        if (StrUtil.isBlank(strPattern) || ArrayUtil.isEmpty(argArray)) {
            return strPattern;
        }
        final int strPatternLength = strPattern.length();
        StringBuilder sbuf = new StringBuilder(strPatternLength + 50);
        int handledPosition = 0;
        int delimIndex;
        for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
            delimIndex = strPattern.indexOf(StrUtil.EMPTY_JSON, handledPosition);
            if (delimIndex == -1) {
                if (handledPosition == 0) {
                    return strPattern;
                }
                sbuf.append(strPattern, handledPosition, strPatternLength);
                return sbuf.toString();
            }

            if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == StrUtil.C_BACKSLASH) {
                if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == StrUtil.C_BACKSLASH) {
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(StrUtil.utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + 2;
                } else {
                    argIndex--;
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(StrUtil.C_DELIM_START);
                    handledPosition = delimIndex + 1;
                }

            } else { // normal placeholder
                sbuf.append(strPattern, handledPosition, delimIndex);
                sbuf.append(StrUtil.utf8Str(argArray[argIndex]));
                handledPosition = delimIndex + 2;
            }


        }
        sbuf.append(strPattern, handledPosition, strPattern.length());
        return sbuf.toString();
    }
}
