package ua.training.model.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordMD5Encoder {

    public String getMD5EncodedPassword(String password){
        MessageDigest digest = null;
        byte[] passbytes = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            passbytes = password.getBytes("utf-8");
        }catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        byte[] digestArray = digest.digest(passbytes);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b:digestArray) {
            stringBuilder.append(String.format("%02X",b));
        }
        return stringBuilder.toString();
    }

}
