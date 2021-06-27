package com.example.demo.chapter10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiaoyun3 on 2018/11/15.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping("/upload/page")
    public String fileUpload(){
        return "/file/upload";
    }

    @RequestMapping("/upload/request")
    @ResponseBody
    public String uploadRequest(MultipartHttpServletRequest request) {
        MultipartFile file = request.getFile("file");
        String submittedFileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(submittedFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "succ";
    }

    @RequestMapping("/upload/multi")
    @ResponseBody
    public String uploadMulti(MultipartFile file) {
        String submittedFileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(submittedFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "succ";
    }

    /**
     * 此场景测试失败，part对象为空
     * @param part
     * @return
     */
    @RequestMapping("/upload/part")
    @ResponseBody
    public String uploadPard(Part part) {
        String submittedFileName = part.getSubmittedFileName();
        try {
            part.write(submittedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "succ";
    }
}
