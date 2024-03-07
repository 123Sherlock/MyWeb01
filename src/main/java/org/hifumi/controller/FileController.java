package org.hifumi.controller;

import cn.hutool.core.io.file.FileNameUtil;
import org.hifumi.domain.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 文件上传
     * 前端必须使用POST，enctype="multipart/form-data"，input type="file"
     *
     * @param file SpringMVC可以自动获取到MultipartFile对象，可以读取到上传文件的信息
     */
    @PostMapping("upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        // 文件扩展名
        String fileExtName = FileNameUtil.extName(file.getOriginalFilename());
        // 为了避免上传同名文件时发生覆盖，使用UUID来命名
        String fileName = UUID.randomUUID() + fileExtName;
        // 一般使用云存储OSS（Object Storage Service）
        file.transferTo(new File("C:\\Users\\" + fileName));
        return Result.success("file URL");
    }
}
