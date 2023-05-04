package com.example.fontend.cos;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
public class PutObject {


    private static COSClient coslient;

    @Autowired
    public void setCoslient(COSClient coslient) {
        this.coslient=coslient;
    }

    public  void putObject(String name) {
        // 初始化用户身份信息(secretId, secretKey)
        String bucketName = "1903247335-1316551243";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,name,new File("E:\\python\\save\\作品\\web\\project\\reggie\\backEnd\\src\\main\\resources\\static\\image\\"+name));
        PutObjectResult putObjectResult = coslient.putObject(putObjectRequest);
        System.out.println(putObjectResult.getRequestId());
    }
}
