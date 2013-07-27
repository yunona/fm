package com.testtask.filemanager.webapp;

import com.testtask.filemanager.domain.FileManagerItem;
import com.testtask.filemanager.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param parent file directory
     * @return files and directories in the specified directory
     */
    @RequestMapping(value = "/subElements.ajax", method = RequestMethod.GET)
    public
    @ResponseBody
    List<FileManagerItem> getSubFileManagerItems(@RequestParam(value = "parent", required = true) String parent) {
        return fileManagerService.getSubFileManagerItems(parent);
    }

    /**
     * Returns an array of {@link FileManagerItem} represention the files and directories in the root directory
     * @param model
     * @return files and directories in the root directory
     */
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String getRootFileManagerItems(Model model) {
        model.addAttribute("items", fileManagerService.getRootFileManagerItems());
        return "filemanager";
    }
}
