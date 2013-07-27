package com.testtask.filemanager.service.impl;

import com.testtask.filemanager.domain.FileItemType;
import com.testtask.filemanager.domain.FileManagerItem;
import com.testtask.filemanager.service.FileManagerService;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 23.07.13
 * Time: 7:52
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FileManagerServiceImpl implements FileManagerService {

    @Override
    public List<FileManagerItem> getSubFileManagerItems(String path) {
        File parent = new File(path);
        if (!parent.exists()) return new ArrayList<FileManagerItem>();
        List<FileManagerItem> subItems = getFileManagerItems(parent.listFiles());
        return subItems;
    }

    @Override
    public List<FileManagerItem> getRootFileManagerItems() {
        List<FileManagerItem> rootItems = getFileManagerItems(File.listRoots());
        return rootItems;
    }

    private List<FileManagerItem> getFileManagerItems(File[] files) {
        List<FileManagerItem> rootItems = new ArrayList<FileManagerItem>();
        for (File file : files) {
            FileManagerItem fileManagerItem = new FileManagerItem(FileItemType.DISC);
            fileManagerItem.setPath(file.getAbsolutePath());
            fileManagerItem.setName(FileSystemView.getFileSystemView().getSystemDisplayName(file));
            fileManagerItem.setHasSubItems(hasFileSubItems(file));
            fileManagerItem.setExtension(getFileExtension(file));
            rootItems.add(fileManagerItem);
        }
        return rootItems;
    }

    private boolean hasFileSubItems(File file) {
        String[] subElements = file.list();
        if (subElements != null && subElements.length > 0) {
            return true;
        }
        return false;
    }

    private String getFileExtension(File file) {
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
