package com.testtask.filemanager.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 23.07.13
 * Time: 7:38
 * To change this template use File | Settings | File Templates.
 */
public class FileManagerItem {

    //private final UUID id = UUID.randomUUID();
    private String name = "";
    private String extension = "";
    private String path = "";
    //private FileManagerItem parentItem;
    private FileItemType type = FileItemType.FILE;
    private boolean hasSubItems;

    public FileManagerItem(FileItemType type) {
        this.type = type;
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

    public FileItemType getType() {
        return type;
    }

    public void setType(FileItemType type) {
        this.type = type;
    }

    public boolean isHasSubItems() {
        return hasSubItems;
    }

    public void setHasSubItems(boolean hasSubItems) {
        this.hasSubItems = hasSubItems;
    }

    public String getFileIcon() {
        return type.toString().toLowerCase();
    }
}
