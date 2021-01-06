package com.hp.dit.Flight.Application.Form.utilities;

import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

public class Utilities {

    public static final String getPhotoUrl(String imageName) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(imageName)
                .toUriString();
        System.out.println(fileDownloadUri);
        return fileDownloadUri;
    }

    public static String base64Encode(String token) {
        byte[] encodedBytes = Base64Utils.encode(token.getBytes());
        return new String(encodedBytes, Charset.forName("UTF-8"));
    }


    public static String base64Decode(String token) {
        byte[] decodedBytes = Base64Utils.decode(token.getBytes());
        return new String(decodedBytes, Charset.forName("UTF-8"));
    }

    public static boolean ifEmptyField(String str) {
        if (str != null && !"".equals(str.trim()) && !"''".equals(str.trim()) && !"null".equals(str.trim())
                && !str.isEmpty() && !"0".equalsIgnoreCase(str.trim()))
            return true;

        return false;
    }

    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    public static boolean empty(String s)
    {
        if(s== null || s.trim().equals(""))
            return true;
        else
            return false;
    }
}
