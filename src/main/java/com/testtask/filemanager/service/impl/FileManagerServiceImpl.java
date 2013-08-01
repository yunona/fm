package com.testtask.filemanager.service.impl;

import com.testtask.filemanager.domain.FileManagerItem;
import com.testtask.filemanager.domain.FileManagerState;
import com.testtask.filemanager.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 23.07.13
 * Time: 7:52
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FileManagerServiceImpl implements FileManagerService {

    @Autowired
    //fileManagerState for curretn session
    private FileManagerState fileManagerState;


    @Override
    public List<FileManagerItem> getSubFileManagerItems(String path) throws IOException {
        fileManagerState.addOpenedItemPath(path);
        FileManagerItesLoadStrategy loadStrategy = FileManagerItesLoadStrategy.getFileManagerItemsLoadStrategy(path);
        return loadStrategy.getSubFileManagerItems(path);
    }

    @Override
    public List<FileManagerItem> getInitialFileManagerItems(String root) throws IOException {
        List<FileManagerItem> rootItems;
        if (StringUtils.isEmpty(root) || root.equals(File.separator)) {
            boolean isUsePathForName = true;
            rootItems = BasicFileManagerItesLoadStrategy.getSubFileManagerItems(File.listRoots(), isUsePathForName);
        } else {
            rootItems = getSubFileManagerItems(root);
        }
        addOpenedItems(rootItems);
        return rootItems;
    }

    @Override
    public void closeFileManagerItems(String path) {
        fileManagerState.close(path);
    }

    private void addOpenedItems(List<FileManagerItem> items) throws IOException {
        for (FileManagerItem item : items) {
            if (fileManagerState.isOpen(item.getPath())) {
                List<FileManagerItem> subItems = getSubFileManagerItems(item.getPath());
                item.setSubItems(subItems);
                addOpenedItems(subItems);
            }
        }
    }
}
