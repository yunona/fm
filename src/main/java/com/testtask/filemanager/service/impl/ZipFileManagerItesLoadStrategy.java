package com.testtask.filemanager.service.impl;

import com.testtask.filemanager.domain.FileManagerItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 28.07.13
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class ZipFileManagerItesLoadStrategy extends FileManagerItesLoadStrategy {
    /**
     * currently method returns all inside elements together not cosidering hierarchy //TODO
     * @param path
     * @return
     */
    @Override
    public List<FileManagerItem> getSubFileManagerItems(String path) throws IOException {
        ZipFile zipFile = new ZipFile(path);
        Enumeration zipEntries = zipFile.entries();
        List<FileManagerItem> fileItems = new ArrayList<FileManagerItem>();
        while (zipEntries.hasMoreElements()) {
            ZipEntry zipEntry = ((ZipEntry) zipEntries.nextElement());
            FileManagerItem fileManagerItem = new FileManagerItem();
            fileManagerItem.setName(zipEntry.getName());
            fileManagerItem.setExpandable(zipEntry.isDirectory());
            fileItems.add(fileManagerItem);
        }
        return fileItems;
    }
}
