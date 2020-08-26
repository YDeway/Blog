package com.deway.blog.tool.instance;

import com.deway.blog.config.MultiPartConfig;
import com.deway.blog.entity.FileRecord;
import com.deway.blog.service.FileRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Base64;

@Component
@AllArgsConstructor
public class FileUtil {

    private final MultiPartConfig config;

    private final FileRecordService fileRecordService;

    /***
     * 将文件保存到磁盘并记录到数据库中
     *
     * @todo 支持的文件类型
     * @param file 待保存的文件
     * @return 文件地址在数据库保存的ID，若失败,则返回null
     */
    public Long saveFile(MultipartFile file) {
        //@todo 这里直接编码了，看看lookup注入
        var record = new FileRecord();
        if(!file.isEmpty()) {
            var originName = file.getOriginalFilename();
            try {
                var absolutePath = Base64
                    .getEncoder()
                    .encodeToString((System.currentTimeMillis() + originName)
                    .getBytes())
                    .replace('/', 'l')
                    .replace('+', 'b')
                    .replace('=', 'q');
                file.transferTo(new File(config.getBaseUploadPath() + absolutePath));
                record.setPath(config.getBaseUploadPath() + absolutePath);
                fileRecordService.create(record);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return record.getId();
    }

    /**
     *  try resource 可以不捕获异常<br>
     *  将指定文件写出到指定的流
     *
     * @param path 文件路径
     * @param ops file待被写出的流
     * @throws IOException 文件未找到、其他读写IO异常
     */
    @Deprecated
    public void readFile(String path, OutputStream ops) throws IOException {
        try (var fis = new FileInputStream(path)){
            var b = new byte[1024 * 1024];
            var len = 0;
            while (( len = fis.read(b)) > 0) {
                ops.write(b, 0 , len);
            }
        }
    }

}
