package com.mall.thirdparty.controller;

import com.common.dto.FileDTO;
import com.common.utils.R;
import com.mall.thirdparty.service.AwsS3FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : Yang
 * @Date :  2023/4/19 8:28
 * @Description：
 */

@RestController
@RequestMapping("third/aws/")
@AllArgsConstructor
public class AwsS3FileController {

    private AwsS3FileService awsS3FileService;

    /**
     * 列表
     */
    @RequestMapping("/upload")
    // @RequiresPermissions("product:attrgroup:list")   
    public R upload(@RequestParam("file") MultipartFile file) {
        R upload = awsS3FileService.upload(file);

        return upload;
    }

}
