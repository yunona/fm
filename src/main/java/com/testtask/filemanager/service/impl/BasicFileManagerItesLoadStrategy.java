package com.testtask.filemanager.service.impl;

import com.testtask.filemanager.domain.FileManagerItem;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 28.07.13
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public class BasicFileManagerItesLoadStrategy extends FileManagerItesLoadStrategy {
    @Override
    public List<FileManagerItem> getSubFileManagerItems(String path) {
        boolean isUsePathForName = false;
        File parent = new File(path);
        return getSubFileManagerItems(parent.listFiles(), isUsePathForName);
    }

    public static List<FileManagerItem> getSubFileManagerItems(File[] files, boolean isUsePathForName) {
        List<FileManagerItem> fileItems = new ArrayList<FileManagerItem>();
        for (File file : files) {
            FileManagerItem fileManagerItem = new FileManagerItem();
            fileManagerItem.setPath(file.getAbsolutePath());
            fileManagerItem.setDirectory(file.isDirectory());
            if (isUsePathForName) {
                fileManagerItem.setName(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 1));
            } else {
                fileManagerItem.setName(FileSystemView.getFileSystemView().getSystemDisplayName(file));
            }
            fileManagerItem.setExtension(FileUtils.getFileExtension(file));
            if (directoryPossibleExtensions.contains(fileManagerItem.getExtension())) {
                fileManagerItem.setExpandable(Boolean.TRUE);
            } else {
                fileManagerItem.setExpandable(FileUtils.hasFileSubItems(file));
            }
            fileItems.add(fileManagerItem);
        }
        return fileItems;
    }
}
