package com.common.dto;

import lombok.Data;

import java.io.InputStream;

/**
 * @Author : Yang
 * @Date :  2023/4/19 8:33
 * @Description：
 */
@Data
public class FileDTO {
    public String path;
    public String fileName;
    public InputStream inputStream;
}
