package com.pixogram.media.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.media.dto.ConsumerMediaDTO;
import com.pixogram.media.entity.Media;
import com.pixogram.media.service.MediaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/producer")
@Slf4j
public class ProducerController {

	@Autowired
	private MediaService mediaService;
	
    @GetMapping
    public String healthCheck() {
        return "Feign Producer is working fine ";
    }
    
	/*
	 * @GetMapping(value = "/media/allMediaByUserId/{userId}") public
	 * List<ConsumerMediaDTO> findMediaByUserId(@PathVariable("userId") Long userId)
	 * { log.info("Inside findMediaById method of MediaController");
	 * System.out.println("Hi.. i am in producer method ....userId:"+userId);
	 * 
	 * List<ConsumerMediaDTO> consumerMediaDTOList = new
	 * ArrayList<ConsumerMediaDTO>(); List<Media> mediaList=
	 * mediaService.findMediaByUserId(userId);
	 * 
	 * for(Media media:mediaList) { ConsumerMediaDTO consumerMediaDTO = new
	 * ConsumerMediaDTO(); consumerMediaDTO.setHide(media.isHide());
	 * consumerMediaDTO.setMediaCaption(media.getMediaCaption());
	 * consumerMediaDTO.setMediaId(media.getMediaId());
	 * consumerMediaDTO.setMediaTitle(media.getMediaTitle());
	 * consumerMediaDTO.setMediaUrl(media.getMediaUrl());
	 * consumerMediaDTO.setMimeType(media.getMimeType());
	 * consumerMediaDTO.setName(media.getName());
	 * consumerMediaDTO.setUploadedDateTime(media.getUploadedDateTime());
	 * consumerMediaDTO.setUserId(media.getUserId());
	 * consumerMediaDTOList.add(consumerMediaDTO); } return consumerMediaDTOList; }
	 */
}
