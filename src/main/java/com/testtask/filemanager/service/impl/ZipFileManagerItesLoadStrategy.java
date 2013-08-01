package com.testtask.filemanager.service.impl;

import com.testtask.filemanager.domain.FileManagerItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
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

    private static final Logger log = LoggerFactory.getLogger(ZipFileManagerItesLoadStrategy.class);

    private static final String PATH_ZIP_EXTENSION = "." + ZIP_EXTENSION;

    @Override
    public List<FileManagerItem> getSubFileManagerItems(String path) throws IOException {
        List<FileManagerItem> fileItems = new ArrayList<FileManagerItem>();
        ZipFile zipFile = null;
        try {
            String zipPath = path.substring(0, path.lastIndexOf(PATH_ZIP_EXTENSION) + PATH_ZIP_EXTENSION.length());
            zipFile = new ZipFile(zipPath);

            List<ZipEntry> subZipEntries = getSubEntries(path, zipFile);
            fileItems = getFileManagerItems(path, zipFile, subZipEntries);

        } catch (IOException e) {
            log.error("IOException for zipFile " + path, e);
        } finally {
            try {
                if (zipFile != null)
                    zipFile.close();
            } catch (IOException e) {
                log.error("attempt to close zipFile", e);
            }
        }


        return fileItems;
    }

    private List<FileManagerItem> getFileManagerItems(String path, ZipFile zipFile, List<ZipEntry> subZipEntries) {
        Set<FileManagerItem> fileItemsSet = new HashSet<FileManagerItem>();
        for (ZipEntry zipEntry : subZipEntries) {
            String[] names = zipEntry.getName().split(INCOMING_PATH_FILE_SEPARTATOR);
            String name = names[names.length - 1];

            FileManagerItem fileManagerItem = new FileManagerItem();
            fileManagerItem.setName(name);
            fileManagerItem.setExpandable(isExpandable(zipFile.entries(), zipEntry.getName()));
            fileManagerItem.setDirectory(zipEntry.isDirectory());
            fileManagerItem.setPath(path + File.separator + name);
            fileItemsSet.add(fileManagerItem);
        }

        List<FileManagerItem> fileItems = new ArrayList<FileManagerItem>();
        fileItems.addAll(fileItemsSet);
        Collections.sort(fileItems, new FileManagerItemBaseComparator());
        return fileItems;
    }

    private List<ZipEntry> getSubEntries(String path, ZipFile zipFile) throws IOException {
        List<ZipEntry> subZipEntries = new ArrayList<ZipEntry>();

        String zipFolder = getZipFolderName(path);
        int levelToInclude = getDeepthLevel(zipFolder) + 1;

        Enumeration zipEntries = zipFile.entries();
        while (zipEntries.hasMoreElements()) {
            ZipEntry zipEntry = ((ZipEntry) zipEntries.nextElement());

            if (!zipEntry.getName().startsWith(zipFolder)) continue;

            int curLevel = getDeepthLevel(zipEntry.getName());
            if (curLevel != levelToInclude) continue;

            subZipEntries.add(zipEntry);
        }
        return subZipEntries;
    }

    private String getZipFolderName(String path) {
        String zipFolder = path.substring(path.lastIndexOf(PATH_ZIP_EXTENSION) + PATH_ZIP_EXTENSION.length());
        zipFolder = modifyZipFolderName(zipFolder);
        return zipFolder;
    }

    private boolean isExpandable(Enumeration zipEntries, String zipEntryName) {
        boolean foundOnce = false;
        while (zipEntries.hasMoreElements()) {
            ZipEntry zipEntry = ((ZipEntry) zipEntries.nextElement());
            String subEntryName = zipEntry.getName();
            if (subEntryName.startsWith(zipEntryName)) {
                if (foundOnce) return true;
                foundOnce = true;
            }
        }
        return false;
    }

    private String modifyZipFolderName(String zipFolder) {
        if (zipFolder.startsWith(File.separator)) zipFolder = zipFolder.substring(1);
        zipFolder = zipFolder.replace(File.separator, INCOMING_PATH_FILE_SEPARTATOR);
        return zipFolder;
    }

    private int getDeepthLevel(String name) {
        return StringUtils.isEmpty(name) ? 0 : name.split(INCOMING_PATH_FILE_SEPARTATOR).length;
    }
}
