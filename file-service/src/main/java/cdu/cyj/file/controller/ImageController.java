package cdu.cyj.file.controller;

import cdu.cyj.file.utils.AliyunOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/img")
public class ImageController {

    @Autowired
    private AliyunOssUtil aliyunOssUtil;

    @PostMapping("/upload")
    public String imgUpload(@RequestParam("file") MultipartFile file) {
        String upload = "";
        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                if (!"".equals(fileName.trim())) {
                    //直接通过输入流上传文件
                    upload = aliyunOssUtil.upload(file.getInputStream(), fileName);

                    //图片回显地址:
                    //http://yiyige.oss-cn-hangzhou.aliyuncs.com/images/2019-10-21/6c964702b67d4eeb920e7f1f4358189b-dishu.jpg
                    System.out.println("path=" + upload);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return upload;
    }
}
