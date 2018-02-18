package com.viniciusalvess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Vini on 18/06/2017.
 */
@Service
public class I18NService {
    @Autowired
    MessageSource messageSource;

    public String getMessage(String aMsgName){
        return messageSource.getMessage(aMsgName, new Object[]{""}, LocaleContextHolder.getLocale());
    }
}
