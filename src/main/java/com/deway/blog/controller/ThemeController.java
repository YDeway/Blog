package com.deway.blog.controller;

import com.deway.blog.entity.BackGroundImage;
import com.deway.blog.service.BackGroundImageService;
import com.deway.blog.tool.HttpStatus;
import com.deway.blog.tool.R;
import com.deway.blog.tool.instance.FileUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Deway
 */
@AllArgsConstructor
@RestController
@RequestMapping("/theme")
public class ThemeController {

    private final FileUtil fileUtil;
    private final BackGroundImageService imageService;

    @PostMapping("/image")
    public R<?> bgImageUpload(@RequestParam("file") MultipartFile file, BackGroundImage image) {
        var filePathId = fileUtil.saveFile(file);
        if(filePathId != null) {
            image.setFileRecordId(filePathId);
            if(imageService.create(image)) {
                return R.response(HttpStatus.SUCCESS, "");
            }
        }
        return R.response(HttpStatus.FAILURE, "");
    }

}
