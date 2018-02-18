package com.viniciusalvess.controllers;

import com.viniciusalvess.entities.FileUpload;
import com.viniciusalvess.pojos.forms.FileUploadForm;
import com.viniciusalvess.services.FileUploadService;
import com.viniciusalvess.services.I18NService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class FileUploadController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    I18NService i18NService;

    @Autowired
    FileUploadService fileUploadService;

    @GetMapping("/")
    public ModelAndView index() {
        return this.upload();
    }

    @GetMapping("/all")
    public ModelAndView showReceivedAll() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("files", this.fileUploadService.findAll());
        mav.setViewName("file_upload_list");
        return mav;
    }

    @GetMapping("/upload")
    public ModelAndView upload() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("fileUploadForm", new FileUploadForm());
        mav.setViewName("file_upload");
        return mav;
    }

    @PostMapping("/upload")
    public ModelAndView doUpload(@Valid FileUploadForm fileUploadForm, BindingResult bindingResult) {
        ModelAndView mav = this.upload();
        if (bindingResult.hasErrors()) {
            mav.addObject("fileUploadForm", fileUploadForm);
            return mav;
        }

        try {
            this.fileUploadService.saveFiles(fileUploadForm);
        } catch (IOException e) {
            logger.error("Failed to upload file", e);
            bindingResult.rejectValue("names", e.getMessage(), String.format("Failed to upload: %s", e.getMessage()));
            e.printStackTrace();
            return mav;
        }

        // add success notification
        return mav;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> download(@PathVariable(name = "id") final Long id){
        try {
            FileUpload f = this.fileUploadService.findOne(id);
            File file = new File(f.getPath());
            final long resourceLength = file.length();

            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            respHeaders.setContentLength(resourceLength);
            respHeaders.setContentDispositionFormData("attachment", f.getNameForDownload());

            InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
            return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            logger.error("Failed to download file", e);
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}