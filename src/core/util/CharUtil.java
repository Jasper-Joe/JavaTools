package core.util;

import core.text.CharPool;

public class CharUtil implements CharPool {
    public static boolean isAscii(char ch) {
        return ch < 128;
    }

    public static boolean isLetterUpper(final char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    public static boolean isLetterLower(final char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    public static boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static boolean isHexChar(char ch) {
        return isNumber(ch) || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F');
    }

    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c== '\u202a'
                || c == '\u0000';
    }

    public static boolean equals(char c1, char c2, boolean ignoreCase) {
        if (ignoreCase) {
            return Character.toLowerCase(c1) == Character.toLowerCase(c2);
        }
        return c1 == c2;
    }


}
