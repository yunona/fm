package com.testtask.filemanager.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 23.07.13
 * Time: 7:38
 * To change this template use File | Settings | File Templates.
 */
public class FileManagerItem implements Serializable {

    private String name = "";
    private String extension = "";
    private String path = "";
    private boolean isExpandable;
    private List<FileManagerItem> subItems = new ArrayList<FileManagerItem>();
    private boolean isDirectory;

    public FileManagerItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        this.isExpandable = expandable;
    }

    public List<FileManagerItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<FileManagerItem> subitems) {
        this.subItems = subitems;
    }

    public void addSubItem(FileManagerItem subitem) {
        subItems.add(subitem);
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
