package com.pixogram.media.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mediaId;
    private Long userId;
    private String mediaUrl;
    private String mimeType;
    private String mediaTitle;
    private String mediaCaption;
    private Date uploadedDateTime;
    private boolean hide;
    private String name;
	/*
	 * @Lob private byte[] data;
	 */
}
