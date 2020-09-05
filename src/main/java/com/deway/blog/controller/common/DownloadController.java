package com.deway.blog.controller.common;

import com.deway.blog.service.FileRecordService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Deway
 */
@RestController
@AllArgsConstructor
@RequestMapping("/download")
public class DownloadController {

    private final FileRecordService fileService;

    @GetMapping("/{id}")
    public ResponseEntity<?> downLoad(@PathVariable("id") Long id) throws IOException {
        var filePath = fileService.getFilePath(id);
        var bytes = new byte[0];
        try {
            bytes = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            if(e instanceof FileNotFoundException) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new HashMap<>(1) {
                            {
                                put("message", "404 Not Found");
                            }
                        });
            }
            else {
                throw e;
            }
        }
        var type = MediaType.parseMediaType(new Tika().detect(bytes));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(bytes.length)
                .contentType(type)
                .body(bytes);
    }

}
