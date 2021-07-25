package com.pixogram.media.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.media.dto.ConsumerMediaDTO;
import com.pixogram.media.dto.MediaDTO;
import com.pixogram.media.dto.MediaRequest;
import com.pixogram.media.dto.MultipleMediaRequest;
import com.pixogram.media.dto.ResponseMessage;
import com.pixogram.media.dto.SingleMediaRequest;
import com.pixogram.media.entity.Media;
import com.pixogram.media.service.MediaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/producer/media")
@Slf4j
public class MediaController {

	@Autowired
	private MediaService mediaService;

	@GetMapping
	public String healthCheck() {
		return "Feign Producer is working fine ";
	}

	@PostMapping(value = "/singleFileUpload")
	public ResponseEntity<ResponseMessage> uploadSingleFile(@ModelAttribute SingleMediaRequest singleMediaRequest) {
		try {
			log.info("inside upload api -uploadFile method ");
			System.out.println("file.getOriginalFilename():" + singleMediaRequest.getFile().getOriginalFilename());
			mediaService.uploadFile(singleMediaRequest);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseMessage("Uploaded the file successfully: " + singleMediaRequest.getFile().getOriginalFilename()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
					new ResponseMessage("Could not upload the file: " + singleMediaRequest.getFile().getOriginalFilename() + "!"));
		}
	}

	@PostMapping(value = "/multipleFileUpload")
	public ResponseEntity<ResponseMessage> uploadMultipleFile(@ModelAttribute MultipleMediaRequest multipleMediaRequest) {
		System.out.println("media-service : uploadMultipleFile method called..");
		
		log.info("Inside uploadMultipleFile method of MediaController.....");
		try {
			for (SingleMediaRequest mediaDTO : multipleMediaRequest.getMediaList()) {
				String fileName = mediaDTO.getFile().getOriginalFilename();
				System.out.println("fileName:" + fileName);
				mediaService.uploadFile(mediaDTO);
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("Uploaded multiple files successfully"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseMessage("Could not upload the file: !"));
		}
	}

	@GetMapping(value = "/allMediaByUserId/{userId}")
	public List<ConsumerMediaDTO> findMediaByUserId(@PathVariable("userId") Long userId) {
		log.info("Inside findMediaById method of MediaController");
		System.out.println("Hi.. i am in producer method ....userId:" + userId);

		List<ConsumerMediaDTO> consumerMediaDTOList = new ArrayList<ConsumerMediaDTO>();
		List<Media> mediaList = mediaService.findMediaByUserId(userId);

		for (Media media : mediaList) {
			ConsumerMediaDTO consumerMediaDTO = new ConsumerMediaDTO();
			consumerMediaDTO.setHide(media.isHide());
			consumerMediaDTO.setMediaCaption(media.getMediaCaption());
			consumerMediaDTO.setMediaId(media.getMediaId());
			consumerMediaDTO.setMediaTitle(media.getMediaTitle());
			consumerMediaDTO.setMediaUrl(media.getMediaUrl());
			consumerMediaDTO.setMimeType(media.getMimeType());
			consumerMediaDTO.setName(media.getName());
			consumerMediaDTO.setUploadedDateTime(media.getUploadedDateTime());
			consumerMediaDTO.setUserId(media.getUserId());
			consumerMediaDTOList.add(consumerMediaDTO);
		}
		return consumerMediaDTOList;
	}

	/*
	 * @GetMapping(value = "/allMediaByUserId/{userId}") public List<Media>
	 * findMediaByUserId(@PathVariable("userId") Long userId) {
	 * log.info("Inside findMediaById method of MediaController");
	 * System.out.println("In media controller.....userId:"+userId); return
	 * mediaService.findMediaByUserId(userId); }
	 */

}
