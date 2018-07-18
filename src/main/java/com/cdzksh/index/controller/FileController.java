package com.cdzksh.index.controller;

import com.cdzksh.index.domain.ResultVO;
import com.cdzksh.index.util.OssUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author Created by ShadowSaint on 2018/7/14
 */
@RestController
public class FileController {
    @RequestMapping("/file/upload")
    public ResultVO fileUpload(HttpServletRequest request){
        ResultVO resultVO = new ResultVO();
        try {
            MultipartHttpServletRequest multipartHttpServletRequest= (MultipartHttpServletRequest) request;
            MultipartFile file = multipartHttpServletRequest.getFile("file");
            String filename=file.getOriginalFilename().toLowerCase();

            File localFolder = new File(FileController.class.getResource("").toString());
            localFolder = localFolder.getParentFile();
            localFolder = localFolder.getParentFile();
            localFolder = localFolder.getParentFile();
            localFolder = localFolder.getParentFile();
            String folderPath = localFolder.getAbsolutePath().split("file:")[1] + File.separator + "upload" + File.separator + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")) + "_" + filename;
            localFolder = new File(folderPath);
            if (!localFolder.exists()) {
                localFolder.mkdirs();
            }
            String filePath=folderPath + File.separator + filename;
            File localFile = new File(filePath);
            file.transferTo(localFile);

            String ossUrl= OssUtil.getOSSUrl(filePath,"."+getSuffix(file.getContentType()));
            resultVO.setData(ossUrl);

            localFile.delete();
        } catch (Exception e) {
            resultVO.setMessage("请求失败");
            resultVO.setStatus(false);
            resultVO.setData(e.getMessage());
        }
        return resultVO;

    }

    private String getSuffix(String contentType){
        try {
            String suffix=contentType;
            if (suffix.contains("png")){
                return "png";
            }
            if (suffix.contains("jpg")){
                return "jpg";
            }
            if (suffix.contains("jpeg")){
                return "jpeg";
            }
            if (suffix.contains("gif")){
                return "gif";
            }
            return "png";
        }catch (Exception e){
            return null;
        }
    }
}
