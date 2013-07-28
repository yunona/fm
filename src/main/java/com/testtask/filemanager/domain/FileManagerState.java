package com.testtask.filemanager.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 28.07.13
 * Time: 8:07
 * To change this template use File | Settings | File Templates.
 */

/**
 * File manager State for the session
 */
public class FileManagerState implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(FileManagerState.class);

    /*
    the longest root will be stored only. e.g. if D:/work was opened and
    D:/work/test was opened the longest (D:/work/test) will be stored for memorey reason
     */
    private Set<String> openItemPaths = new HashSet<String>();

    public void addOpenedItemPath(String path) {
        log.debug("Open Item: " + path);
        removeParentDirecotries(path);
        openItemPaths.add(path);
    }

    private void removeParentDirecotries(String path) {
        for (Iterator<String> savedPathIt = openItemPaths.iterator(); savedPathIt.hasNext(); ) {
            String savedPath = savedPathIt.next();
            if (path.contains(savedPath)) {
                savedPathIt.remove();
            }
        }
    }

    private void removeChildDirecotries(String path) {
        for (Iterator<String> savedPathIt = openItemPaths.iterator(); savedPathIt.hasNext(); ) {
            String savedPath = savedPathIt.next();
            if (savedPath.contains(path)) {
                savedPathIt.remove();
            }
        }
    }

    public void close(String path) {
        log.debug("Close Item: " + path);
        removeChildDirecotries(path);
        addParentDirectory(path);
    }

    private void addParentDirectory(String path) {
        if (path.endsWith(File.separator)) return;
        String parentDirectory = path.substring(0, path.lastIndexOf(File.separator));
        if (parentDirectory.endsWith(":")) parentDirectory += File.separator;
        openItemPaths.add(parentDirectory);
    }

    public boolean isOpen(String path) {
        for (String savedPath : openItemPaths) {
            if (savedPath.contains(path)) {
                return true;
            }
        }
        return false;
    }

}
