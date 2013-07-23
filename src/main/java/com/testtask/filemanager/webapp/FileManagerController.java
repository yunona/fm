package com.testtask.filemanager.webapp;

import com.testtask.filemanager.domain.FileManagerItem;
import com.testtask.filemanager.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/expand/{parent}", method = RequestMethod.GET)
    @ResponseBody
    public List<FileManagerItem> findFavoritesByPig(@PathVariable String parent, @RequestParam int page, @RequestParam int size) {
        return null;
    }

}
