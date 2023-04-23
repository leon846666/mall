package com.mall.thirdparty.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.common.dto.FileDTO;
import com.common.utils.PageUtils;
import com.common.utils.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * @Author : Yang
 * @Date :  2023/4/19 8:35
 * @Description：
 */
@Service
@Slf4j
public class AwsS3FileServiceImpl implements AwsS3FileService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.bucket}")
    private String bucket;


    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public R upload(MultipartFile file) {
        String fileName = null;
        try {
            // Set the canned access control list to public read
            CannedAccessControlList acl = CannedAccessControlList.PublicRead;

            // Create a PutObjectRequest with the key, file, and canned ACL
            int i = file.getOriginalFilename().lastIndexOf('.');
            String suffix = file.getOriginalFilename().substring(i);
            log.info("file suffix {}", suffix);
            fileName = UUID.randomUUID() + suffix;
            PutObjectRequest request = new PutObjectRequest(bucket, fileName, multipartFileToFile(file)).withCannedAcl(acl);

            amazonS3.putObject(request);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        URL url = amazonS3.getUrl(bucket, fileName);
        return new R().ok(url.toString());


    }
}
