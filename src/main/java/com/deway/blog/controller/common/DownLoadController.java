package com.deway.blog.controller.common;

import com.deway.blog.service.FileRecordService;
import com.deway.blog.tool.HttpStatus;
import com.deway.blog.tool.R;
import com.deway.blog.tool.instance.FileUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 *
 * @author Deway
 */
@RestController
@AllArgsConstructor
@RequestMapping("/download")
public class DownLoadController {

    private final FileUtil fileUtil;
    private final FileRecordService fileService;

    /**
     * @todo 这有点错乱呢，都脱离了spring了？
     * @todo 响应文件的mime类型
     *
     */
    @GetMapping
    public void downLoadBgImage(@RequestParam("id") Long id, HttpServletResponse resp) throws IOException {
        var ops = resp.getOutputStream();
        try {
            fileUtil.readFile(fileService.getFilePath(id), ops);
        }
        catch (IOException e) {
            resp.setStatus(404);
            resp.setContentType("application/json;charset=UTF-8");
            ops.write(R.
                response(HttpStatus.NOT_FOUND, new HashMap<>(1) {
                    {
                        put("message", "服务器找不到文件或者其他IO异常，但老子就全当找不到");
                    }
                })
                .toResponseBody()
                .getBytes(StandardCharsets.UTF_8)
            );
        }
    }

}
