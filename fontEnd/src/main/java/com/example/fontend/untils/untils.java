package com.example.fontend.untils;

import java.math.BigInteger;
import java.util.UUID;

public class untils {

    public static Long createdUUID(){
        UUID uuid=UUID.randomUUID();
        BigInteger bigInteger=new BigInteger(uuid.toString().replace("-",""),16);
        String strUUID=String.valueOf(bigInteger).substring(0,14);
        return Long.parseLong(strUUID);
    }
}
