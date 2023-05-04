package com.example.fontend.untils;

import com.example.fontend.cos.PutObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class untils {

    public static Long createdUUID(){
        UUID uuid=UUID.randomUUID();
        BigInteger bigInteger=new BigInteger(uuid.toString().replace("-",""),16);
        String strUUID=String.valueOf(bigInteger).substring(0,14);
        return Long.parseLong(strUUID);
    }
    public static String combinationName(MultipartFile file){
        int originalName=file.getOriginalFilename().indexOf(".");//获取后缀位置
        String suffix=file.getOriginalFilename().substring(originalName,file.getOriginalFilename().length());//截取后缀名字

        String name= UUID.randomUUID()+suffix;
        return name;
    }
    public static void uploadFoodPng(String fileName) throws IOException {
        PutObject putObject = new PutObject();
        putObject.putObject(fileName);//负责发送图到cos中
    }
    public static StringBuffer createRandomNumber(){
        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<5;i++){
            Random random=new Random();
            int number=random.nextInt(10);
            buffer.append(number);
        }
        return buffer;
    }
}
