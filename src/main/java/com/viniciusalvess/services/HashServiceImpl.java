package com.viniciusalvess.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HashServiceImpl implements HashService {

    @Value("${app.password.new.length}")
    private int passwordLength;

    @Override
    public String randomAlphaNumeric(int count) {
        String ALPHA_NUMERIC_STRING = "abcdefghijklemnopqrstuvxyz0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }

        return builder.toString();
    }

    @Override
    public String randomAlphaNumeric() {
        return this.randomAlphaNumeric(passwordLength);
    }
}
