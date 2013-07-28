package com.testtask.filemanager.service.impl;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 28.07.13
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {

    public static boolean hasFileSubItems(File file) {
        String[] subElements = file.list();
        if (subElements != null && subElements.length > 0) {
            return true;
        }
        return false;
    }

    public static String getFileExtension(File file) {
        if (file == null || file.getName() == null) return "";
        Pattern pattern = Pattern.compile("\\.([^.]*)$");
        Matcher matcher = pattern.matcher(file.getName());
        if (matcher.find()) {
            String extension = matcher.group(1);
            return extension;
        }
        return "";
    }
}
