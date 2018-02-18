package com.viniciusalvess.services;

import com.viniciusalvess.entities.FileUpload;
import com.viniciusalvess.pojos.forms.FileUploadForm;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {
    void saveFiles(FileUploadForm fileUploadForm) throws IOException;
    List<FileUpload> findAll();
    FileUpload findOne(Long id);
}
