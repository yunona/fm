package com.testtask.filemanager.domain; /**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 28.07.13
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */

import com.testtask.filemanager.domain.FileManagerState;
import com.testtask.filemanager.service.impl.FileManagerServiceImpl;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Test for {@link FileManagerState}
 */
public class FileManagerStateTest {

    @Test
    public void testIsOpenedParentDir() {
        FileManagerState state = new FileManagerState();
        state.addOpenedItemPath("D:\\work");
        state.addOpenedItemPath("D:\\work\\test");
        assertTrue("Added directory is not opened", state.isOpen("D:\\work\\test"));
        assertTrue("Parent directory is not opened", state.isOpen("D:\\work"));
        assertTrue("Parent of Parent directory is not opened", state.isOpen("D:\\"));
    }

    @Test
    public void testIsClosedChildDir() {
        FileManagerState state = new FileManagerState();
        state.addOpenedItemPath("D:\\work\\test");
        state.close("D:\\");
        assertFalse("Added directory is not closed", state.isOpen("D:\\work\\test"));
        assertFalse("Parent directory is not closed", state.isOpen("D:\\work"));
        assertFalse("Parent of Parent directory is not closed", state.isOpen("D:\\"));

        state.addOpenedItemPath("D:\\work\\test\\abc");
        state.close("D:\\work\\test");
        assertFalse("Added directory is not closed", state.isOpen("D:\\work\\test\\abc"));
        assertFalse("Parent directory is not closed", state.isOpen("D:\\work\\test"));
        assertTrue("Parent of Parent directory is not opened", state.isOpen("D:\\work"));

        state.close("D:\\work");
        assertTrue("Root directory is not opened", state.isOpen("D:\\"));
    }

    @Test
    public void testIsOpenedDirInZip() throws IOException {
        FileManagerState state = new FileManagerState();
        state.addOpenedItemPath("D:\\work\\folder.zip");
        state.addOpenedItemPath("D:\\work\\folder.zip\\folder1");
        assertTrue("Added directory is not opened", state.isOpen("D:\\work\\folder.zip\\folder1"));
        assertTrue("Parent directory is not opened", state.isOpen("D:\\work\\folder.zip"));

        FileManagerServiceImpl service = new FileManagerServiceImpl();
        service.getInitialFileManagerItems("");
    }
}
