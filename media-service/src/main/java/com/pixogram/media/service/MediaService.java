package com.pixogram.media.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pixogram.media.dto.MediaDTO;
import com.pixogram.media.dto.MultipleMediaRequest;
import com.pixogram.media.dto.SingleMediaRequest;
import com.pixogram.media.entity.Media;
import com.pixogram.media.exception.FileStorageException;
import com.pixogram.media.exception.MyFileNotFoundException;
import com.pixogram.media.repository.MediaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;
    
    private Path fileStorageLocation = null;

    public Media uploadFile(SingleMediaRequest media) throws IOException {
  	  System.out.println("in FileStorageService.......");
  	  // Normalize file name
      String fileName = StringUtils.cleanPath(media.getFile().getOriginalFilename());
      
      Media mediaEntity = new Media();
      mediaEntity.setName(fileName);
      mediaEntity.setMimeType(media.getFile().getContentType());
      //mediaEntity.setData(media.getFile().getBytes());
      mediaEntity.setMediaCaption(media.getDesc());
      mediaEntity.setMediaTitle(media.getMediaTitle());
      mediaEntity.setUploadedDateTime(new Date());
      mediaEntity.setUserId(media.getUserId());
      
      System.out.println("to save"+mediaEntity.getName());
      log.info("to save"+mediaEntity.getName());
      
  		try {
  			// Check if the file's name contains invalid characters
  			if (fileName.contains("..")) {
  				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
  			}

  			System.out.println("create directories...");
  			log.info("create directories...");
  			String location = "C:/Users/AnushkhaThakur/Downloads/pixogram";
  			Files.createDirectories(Paths.get(location+fileName).toAbsolutePath().normalize());
  			
  			byte[] bytes = media.getFile().getBytes();
  			Path path = Paths.get(location+"/" + media.getFile().getOriginalFilename());
              Files.write(path, bytes);
     
              mediaEntity.setMediaUrl(location);
              mediaEntity = mediaRepository.save(mediaEntity);
  			
  		}catch (Exception ex) {
          throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
      }
      return mediaEntity;
    }

  	  public Stream<Media> getAllFiles() { return
  			mediaRepository.findAll().stream(); }
  	 

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public List<Media> findMediaByUserId(Long userId) {
        log.info("Inside findByUserId of MediaService");
        System.out.println("in findMediaById method");
        return mediaRepository.findMediaByUserId(userId);
    }
}
