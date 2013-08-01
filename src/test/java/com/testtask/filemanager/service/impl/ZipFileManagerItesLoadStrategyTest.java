package com.testtask.filemanager.service.impl;

import com.testtask.filemanager.domain.FileManagerItem;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 31.07.13
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */

/**
 * Test for {@link ZipFileManagerItesLoadStrategy}
 */
public class ZipFileManagerItesLoadStrategyTest {

    private static final String FILE_NAME = "tmp.zip";

    private void createZipFile() throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(FILE_NAME));
        try {
            out.putNextEntry(new ZipEntry("folder1/"));
            out.putNextEntry(new ZipEntry("folder1/folder2/"));
            out.putNextEntry(new ZipEntry("folder1/folder2/folder3.doc"));
            out.putNextEntry(new ZipEntry("folder1/folder2.doc"));
            out.putNextEntry(new ZipEntry("folder1.doc"));
            out.putNextEntry(new ZipEntry("zipfolder.zip"));
        } finally {
            out.close();
        }
    }

    private void cleanUp() throws IOException {
        File zipFile = new File(FILE_NAME);
        zipFile.delete();
    }

    @Test
    public void testViewZip() throws Exception {
        createZipFile();

        try {

            ZipFileManagerItesLoadStrategy st = new ZipFileManagerItesLoadStrategy();
            List<FileManagerItem> items = st.getSubFileManagerItems(FILE_NAME);
            String[] expectedItems = {"folder1", "folder1.doc", "zipfolder.zip"};
            assertItems(items, expectedItems);

            items = st.getSubFileManagerItems(FILE_NAME + File.separator + "folder1");
            expectedItems = new String[]{"folder2", "folder2.doc"};
            assertItems(items, expectedItems);

            items = st.getSubFileManagerItems(FILE_NAME + File.separator + "folder1" + File.separator + "folder2");
            expectedItems = new String[]{"folder3.doc"};
            assertItems(items, expectedItems);

        } finally {
            cleanUp();
        }
    }

    private void assertItems(List<FileManagerItem> items, String[] expectedItems) {
        int index = 0;
        for (FileManagerItem item : items) {
            String orderedName = expectedItems[index++];
            assertEquals("Wrong elements with index " + index, orderedName, item.getName());
        }
    }
}
