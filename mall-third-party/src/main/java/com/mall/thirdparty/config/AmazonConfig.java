package com.mall.thirdparty.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : Yang
 * @Date :  2023/4/19 8:19
 * @Description：
 */
@Configuration
public class AmazonConfig {

    @Value("${aws.accessKey}")
    private  String accessKey;
    @Value("${aws.secretKey}")
    private  String secretKey;
    @Value("${aws.region}")
    private  String region;
    @Value("${aws.bucket}")
    private  String bucket;


    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}