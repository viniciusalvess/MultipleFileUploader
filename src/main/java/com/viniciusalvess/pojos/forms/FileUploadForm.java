package com.viniciusalvess.pojos.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public @Data class FileUploadForm {

    private Long id;

    @Size(min=1)
    private List<MultipartFile> files;

    private List<String> names;
}
