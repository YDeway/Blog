package com.deway.blog.tool.instance;

import com.deway.blog.config.MultiPartConfig;
import com.deway.blog.entiry.FileRecord;
import com.deway.blog.service.FileRecordService;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Component
@AllArgsConstructor
public class UploadFileUtil {

    private final MultiPartConfig config;

    private final FileRecordService fileRecordService;

    /***
     * 若失败,则返回null
     *
     * @param file 待保存的文件
     * @return 文件地址在数据库保存的ID
     */
    public Long saveFile(MultipartFile file) {
        //@todo 这里直接编码了，看看lookup注入
        var record = new FileRecord();
        if(!file.isEmpty()) {
            var originName = file.getOriginalFilename();
            try {
                var tika = new Tika();
                var detect = tika.detect(file.getBytes());
                var absolutePath = Base64.getEncoder().encodeToString((System.currentTimeMillis() + originName).getBytes());
                absolutePath += "."  + detect.substring(detect.indexOf("/") + 1);
                file.transferTo(new File(config.getBaseUploadPath() + absolutePath));
                record.setPath(config.getBaseUploadPath() + absolutePath);
                fileRecordService.insert(record);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return record.getId();
    }

}
