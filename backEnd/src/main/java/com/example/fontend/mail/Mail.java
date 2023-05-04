package com.example.fontend.mail;
import com.qcloud.cos.utils.Jackson;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ses.v20201002.SesClient;
import com.tencentcloudapi.ses.v20201002.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import com.example.fontend.untils.untils;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Mail

{
   private static String accKey;
   private static String secretKey;
   @Value("${tengxun.accKey}")
   public void setAccKey(String accKey){
       Mail.accKey =accKey;
   }
   @Value("${tengxun.secretKey}")
   public void setSecretKey(String secretKey){
       Mail.secretKey=secretKey;
   }


    public static String sendMail(String mail) {
        try{
            log.info("{},{}",accKey,secretKey);
            Credential cred = new Credential(accKey, secretKey);


            // 实例化要请求产品的client对象,clientProfile是可选的
            SesClient client = new SesClient(cred, "ap-guangzhou");
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendEmailRequest req = new SendEmailRequest();
            req.setFromEmailAddress("test@1903247335.cn");

            String[] destination1 = {mail};
            req.setDestination(destination1);

            Template template1 = new Template();
            template1.setTemplateID(21608L);

            StringBuffer number=untils.createRandomNumber();

            Map<String,String> map=new HashMap<>();

            map.put("code", String.valueOf(number));

            template1.setTemplateData(Jackson.toJsonString(map));

            req.setTemplate(template1);
            req.setSubject("验证码");

            // 返回的resp是一个SendEmailResponse的实例，与请求对象对应
            SendEmailResponse resp = client.SendEmail(req);
            return  String.valueOf(number);
            // 输出json格式的字符串回包

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return "error";
        }
    }
}

