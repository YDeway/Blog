package com.deway.blog.controller;

import com.deway.blog.entiry.BackGroundImage;
import com.deway.blog.service.BackGroundImageService;
import com.deway.blog.tool.HttpStatus;
import com.deway.blog.tool.R;
import com.deway.blog.tool.instance.FileUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author Deway
 */
@AllArgsConstructor
@RestController
@RequestMapping("/theme")
public class ThemeController {

    private final FileUtil fileUtil;
    private final BackGroundImageService imageService;

    @PostMapping("/bgimage")
    public R<?> bgImageUpload(@RequestParam("file") MultipartFile file, BackGroundImage image) {
        var id = fileUtil.saveFile(file);
        if(id != null) {
            image.setId(id);
            if(imageService.create(image)) {
                return R.response(HttpStatus.SUCCESS, "");
            }
        }
        return R.response(HttpStatus.FAILURE, "");
    }

    @GetMapping("/bgimage")
    public R<?> downLoadBgImage(HttpServletResponse resp,  @RequestParam("path") String path) {
        System.out.println(path);
        System.out.println(resp);
        try(var out = resp.getOutputStream()) {
            fileUtil.readFile(path, out);
            return R.response(HttpStatus.SUCCESS, "") ;
        }
        catch(IOException e) {

        }
        return R.response(HttpStatus.SUCCESS, "") ;
    }
}
