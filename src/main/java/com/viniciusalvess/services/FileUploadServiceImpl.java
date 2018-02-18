package com.viniciusalvess.services;

import com.viniciusalvess.entities.FileUpload;
import com.viniciusalvess.pojos.forms.FileUploadForm;
import com.viniciusalvess.repositories.FileUploadRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${app.uploads.path}")
    private String uploadPath;

    @Autowired
    HashService hashService;

    @Autowired
    FileUploadRepository fileUploadRepository;

    @Override
    public void saveFiles(FileUploadForm fileUploadForm) throws IOException {
        File dir = new File(this.getFileDir());

        if(! dir.exists()){
            dir.mkdirs();
        }

        for(int i = 0; i < fileUploadForm.getFiles().size(); i++){
            MultipartFile f = fileUploadForm.getFiles().get(i);
            String newName = fileUploadForm.getNames().get(i).isEmpty() ? f.getOriginalFilename() : fileUploadForm.getNames().get(i);
            String ext = FilenameUtils.getExtension(f.getOriginalFilename());
            File fn = new File(String.format("%s%s%s.%s",
                    dir.getCanonicalPath(),
                    File.separator,
                    this.hashService.randomAlphaNumeric(),
                    ext)
            );

            f.transferTo(fn);
            FileUpload fu = new FileUpload();
            fu.setName(String.format("%s.%s",newName,ext));
            fu.setOriginalName(f.getOriginalFilename());
            fu.setPath(fn.getAbsolutePath());
            this.fileUploadRepository.save(fu);
        }
    }

    private String getFileDir(){
        return this.uploadPath;
    }

    @Override
    public FileUpload findOne(Long id) {
        return this.fileUploadRepository.findOne(id);
    }

    @Override
    public List<FileUpload> findAll() {
        return this.fileUploadRepository.findAll();
    }

}
