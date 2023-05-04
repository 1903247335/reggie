package com.example.fontend.cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;



@Configuration

public class Coslient {
    private static String accKey;

    private static String secretKey;

    @Value("${tengxun.accKey}")
    public void setAccKey(String accKey){
        Coslient.accKey=accKey;
    }


    @Value("${tengxun.secretKey}")
    public void setSecretKey(String secretKey){
        Coslient.secretKey=secretKey;
    }
    @Bean
    public COSClient getCoslient() {

        COSCredentials cred = new BasicCOSCredentials(accKey,secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region("ap-guangzhou"));
        COSClient cosclient = new COSClient(cred, clientConfig);
        return cosclient;
    }
}
