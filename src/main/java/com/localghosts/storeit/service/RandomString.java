package com.localghosts.storeit.service;

import org.springframework.stereotype.Component;

//This utility will be used for OTP generation.
@Component
public class RandomString {

    final String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    public String generateOTP(int n) {
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (charset.length() * Math.random());
            sb.append(charset.charAt(index));
        }

        return sb.toString();
    }
}
