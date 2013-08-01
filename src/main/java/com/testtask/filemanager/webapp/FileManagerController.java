package com.testtask.filemanager.webapp;

import com.testtask.filemanager.domain.FileManagerItem;
import com.testtask.filemanager.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Yunona
 * Date: 23.07.13
 * Time: 7:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/filemanager")
class FileManagerController {

    @Autowired
    private FileManagerService fileManagerService;

    /**
     * Returns an array of {@link FileManagerItem} represention the files and directories in the specified directory
     *
     * @param parent file directory
     * @return files and directories in the specified directory
     * @throws IOException
     */
    @RequestMapping(value = "/subElements.ajax", method = RequestMethod.POST)
    public
    @ResponseBody
    List<FileManagerItem> getSubFileManagerItems(@RequestParam(value = "parent", required = true) String parent) throws IOException {
        return fileManagerService.getSubFileManagerItems(parent);
    }

    /**
     * Returns an array of {@link FileManagerItem} represention initial state of the files and directories
     *
     * @param root
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/initialElements.ajax", method = RequestMethod.POST)
    public
    @ResponseBody
    List<FileManagerItem> getInitialFileManagerItems(@RequestParam(value = "root", required = true) String root) throws IOException {
        return fileManagerService.getInitialFileManagerItems(root);
    }

    /**
     *  Informs the server that a file item was closed
     *
     * @param parent
     * @return
     */
    @RequestMapping(value = "/closeElements.ajax", method = RequestMethod.POST)
    public
    @ResponseBody
    void closeFileManagerItem(@RequestParam(value = "parent", required = true) String parent) {
        fileManagerService.closeFileManagerItems(parent);
    }

    /**
     * Returns an array of {@link FileManagerItem} represention the files and directories in the root directory
     *
     * @return files and directories in the root directory
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String getRootFileManagerItems() throws IOException {
        return "filemanager";
    }
}
