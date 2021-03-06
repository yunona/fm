package com.testtask.filemanager.service;

import com.testtask.filemanager.domain.FileManagerItem;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 23.07.13
 * Time: 7:44
 * To change this template use File | Settings | File Templates.
 */
public interface FileManagerService {

    public List<FileManagerItem> getSubFileManagerItems(String path) throws IOException;

    public List<FileManagerItem> getInitialFileManagerItems(String root) throws IOException;

    public void closeFileManagerItems(String path);
}
