package hrm.wyg.controller;

import hrm.wyg.util.AjaxResult;
import hrm.wyg.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file){
        try {
            String filename = file.getOriginalFilename();
            int i = filename.lastIndexOf(".");
            String extName = filename.substring(i + 1);
            byte[] bytes = file.getBytes();
            String path = FastDfsApiOpr.upload(bytes, extName);
            return AjaxResult.me().setSuccess(true).setMessage("上传成功").setResultObj(path);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败"+e.getMessage());
        }

    }

}
