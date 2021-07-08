package core.io.file;

import core.util.CharUtil;
import core.util.StrUtil;

import java.io.File;

public class FileNameUtil {
    public static final String EXT_JAVA = ".java";

    public static final String EXT_CLASS = ".class";

    public static final char UNIX_SEPARATOR = CharUtil.SLASH;

    public static final char WINDOWS_SEPARATOR = CharUtil.BACKSLASH;

    public static String getName(File file) {
        return (file != null) ? file.getName() : null;
    }

    public static String extName(String fileName) {
        if (fileName == null) {
            return null;
        }

        int index = fileName.lastIndexOf(StrUtil.DOT);
        if (index == -1) {
            return StrUtil.EMPTY;
        } else {
            String ext = fileName.substring(index + 1);
            return StrUtil.containsAny(ext, UNIX_SEPARATOR, WINDOWS_SEPARATOR) ? StrUtil.EMPTY : ext;
        }
    }

    public static String extName(File file) {
        if (file == null) {
            return null;
        }
        if (file.isDirectory()) {
            return null;
        }
        return extName(file.getName());
    }

    public static String getSuffix(File file) {
        return extName(file);
    }

    public static String mainName(File file) {
        if (file.isDirectory()) {
            return file.getName();
        }
        return mainName(file.getName());
    }
}
