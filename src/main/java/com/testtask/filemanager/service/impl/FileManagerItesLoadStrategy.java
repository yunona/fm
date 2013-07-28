package com.testtask.filemanager.service.impl;

import com.testtask.filemanager.domain.FileManagerItem;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 28.07.13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class FileManagerItesLoadStrategy {

    private static final String ZIP_EXTENSION = "zip";
    private static final String RAR_EXTENSION = "rar";

    protected static Set<String> directoryPossibleExtensions = new HashSet<String>();

    {
        directoryPossibleExtensions.add(RAR_EXTENSION);
        directoryPossibleExtensions.add(ZIP_EXTENSION);
    }

    public abstract List<FileManagerItem> getSubFileManagerItems(String path) throws IOException;

    public static final FileManagerItesLoadStrategy getFileManagerItesLoadStrategy(String extension) {
        if (ZIP_EXTENSION == extension) {
            return new ZipFileManagerItesLoadStrategy();
        } else {
            return new BasicFileManagerItesLoadStrategy();
        }
    }
}
