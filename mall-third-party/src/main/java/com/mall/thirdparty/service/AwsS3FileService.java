package com.mall.thirdparty.service;

import com.common.dto.FileDTO;
import com.common.utils.PageUtils;
import com.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

public interface AwsS3FileService {
    R upload(MultipartFile file);
}
