package com.pixogram.user.dto;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleMediaRequest {

    private Long userId;
    private String mediaTitle;
    private String desc;
    private String [] tags;
    private String [] effect;
    private MultipartFile file;
}
