package com.testtask.filemanager.service.impl;

import com.testtask.filemanager.domain.FileManagerItem;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
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

    protected static final String ZIP_EXTENSION = "zip";
    protected static final String RAR_EXTENSION = "rar";
    protected static final String INCOMING_PATH_FILE_SEPARTATOR = "/";

    protected static Set<String> directoryPossibleExtensions = new HashSet<String>();

    {
        directoryPossibleExtensions.add(RAR_EXTENSION);
        directoryPossibleExtensions.add(ZIP_EXTENSION);
    }

    public abstract List<FileManagerItem> getSubFileManagerItems(String path) throws IOException;

    public static final FileManagerItesLoadStrategy getFileManagerItemsLoadStrategy(String path) {
        if (path.endsWith(ZIP_EXTENSION) || path.contains(ZIP_EXTENSION + File.separator)) {
            return new ZipFileManagerItesLoadStrategy();
        } else {
            return new BasicFileManagerItesLoadStrategy();
        }
    }

    static class FileManagerItemBaseComparator implements Comparator<FileManagerItem> {
        /**
         * sorts List to get the directores go first, then archives and then files (thus in acs order directory <  file)
         */
        @Override
        public int compare(FileManagerItem item1, FileManagerItem item2) {
            if (item1.isDirectory() && item2.isDirectory()) {
                return compareDirectories(item1, item2);
            } else if (item1.isDirectory()) {
                return -1;
            } else if (item2.isDirectory()) {
                return 1;
            } else {
                return compareFiles(item1, item2);
            }
        }

        private int compareDirectories(FileManagerItem item1, FileManagerItem item2) {
            return compareItemsByNameAndExtentions(item1, item2);
        }

        private int compareFiles(FileManagerItem item1, FileManagerItem item2) {
            if (item1.isExpandable() && !item2.isExpandable()) {
                return -1;
            } else if (!item1.isExpandable() && item2.isExpandable()) {
                return 1;
            }

            return compareItemsByNameAndExtentions(item1, item2);
        }

        private int compareItemsByNameAndExtentions(FileManagerItem item1, FileManagerItem item2) {
            int nameComparisonResult = item1.getName().compareToIgnoreCase(item2.getName());
            if (nameComparisonResult == 0) {
                return item1.getExtension().compareToIgnoreCase(item2.getExtension());
            } else {
                return nameComparisonResult;
            }
        }
    }
}
