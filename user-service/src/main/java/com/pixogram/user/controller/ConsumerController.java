package com.pixogram.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.user.dto.MultipleMediaRequest;
import com.pixogram.user.dto.ProducerMediaDTO;
import com.pixogram.user.dto.SingleMediaRequest;
import com.pixogram.user.service.UserService;

@RequestMapping("/consumer")
@RestController
public class ConsumerController {

	private UserService service;

	@Autowired
	public ConsumerController(UserService service) {
		this.service = service;
	}

	@GetMapping("/getMessage")
	public String getMessage() {
		return service.getMessageFromProducer();
	}

	@PostMapping("/singleFileUpload")
	public String singleMediaUpload(@ModelAttribute SingleMediaRequest singleMediaRequest) {
		return service.singleFileUpload(singleMediaRequest);
	}

	@PostMapping("/multipleFileUpload")
	public String multipleFileUpload(@ModelAttribute MultipleMediaRequest multipleMediaRequest) {
		return service.multipleFileUpload(multipleMediaRequest);
	}

	@GetMapping("/allMediaByUserId/{userId}")
	public List<ProducerMediaDTO> allMediaByUserId(@PathVariable("userId") Long userId) {
		return service.allMediaByUserId(userId);
	}
}
