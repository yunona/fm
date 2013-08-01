package com.testtask.filemanager.service.impl;

import com.testtask.filemanager.domain.FileManagerItem;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 01.08.13
 * Time: 8:47
 * To change this template use File | Settings | File Templates.
 */

/**
 * Test for {@link FileManagerItesLoadStrategy.FileManagerItemBaseComparator}
 */
public class FileManagerItemBaseComparatorTest {

    @Test
    public void testSortingOrder() throws IOException {
        FileManagerItesLoadStrategy.FileManagerItemBaseComparator comparator = new FileManagerItesLoadStrategy.FileManagerItemBaseComparator();
        Set<FileManagerItem> sortedSet = new TreeSet<FileManagerItem>(comparator);
        FileManagerItem itemB = new FileManagerItem();
        itemB.setName("b");
        sortedSet.add(itemB);

        FileManagerItem itemA = new FileManagerItem();
        itemA.setName("a");
        sortedSet.add(itemA);

        FileManagerItem itemC = new FileManagerItem();
        itemC.setName("c");
        sortedSet.add(itemC);

        String[] orderedNames = {"a", "b", "c"};
        assertOrder(sortedSet, orderedNames);
    }

    private void assertOrder(Set<FileManagerItem> sortedSet, String[] orderedNames) {
        int index = 0;
        for (FileManagerItem item : sortedSet) {
            String orderedName = orderedNames[index++];
            assertEquals("Wrong elements with index " + index, orderedName, item.getName());
        }
    }

    @Test
    public void testDirectoryExpandableAndFolderOrder() throws IOException {
        FileManagerItesLoadStrategy.FileManagerItemBaseComparator comparator = new FileManagerItesLoadStrategy.FileManagerItemBaseComparator();
        Set<FileManagerItem> sortedSet = new TreeSet<FileManagerItem>(comparator);
        FileManagerItem itemZip = new FileManagerItem();
        itemZip.setName("bzipFile");
        itemZip.setExpandable(true);
        sortedSet.add(itemZip);

        FileManagerItem itemFile = new FileManagerItem();
        itemFile.setName("afile");
        sortedSet.add(itemFile);

        FileManagerItem itemDir = new FileManagerItem();
        itemDir.setName("cdir");
        itemDir.setDirectory(true);
        sortedSet.add(itemDir);

        String[] orderedNames = {"cdir", "bzipFile", "afile"};
        assertOrder(sortedSet, orderedNames);
    }
}
