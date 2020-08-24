package com.deway.blog.controller.common;

import com.deway.blog.service.FileRecordService;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *
 * @author Deway
 */
@RestController
@AllArgsConstructor
@RequestMapping("/download")
public class DownLoadController {

    private final FileRecordService fileService;

    /**
     * @todo 这有点错乱呢，都脱离了spring了？
     * @todo 响应文件的mime类型
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> downLoad(@PathVariable("id") Long id) {
        var filePath = fileService.getFilePath(id);
        byte[] bytes = new byte[0];
        try {
            bytes = new FileInputStream(new File(filePath)).readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        var type = MediaType.parseMediaType(new Tika().detect(bytes));
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(bytes.length)
                .contentType(type)
                .body(bytes);
    }

    @GetMapping("xxxx")
    public void XX(HttpServletResponse resp) throws IOException {
        resp.getWriter().write("草拟吗");

    }
    @GetMapping("yyyy")
    public String yyy(HttpServletResponse resp) {
        return "草拟吗";

    }
}
