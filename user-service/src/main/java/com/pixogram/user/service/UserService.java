package com.pixogram.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.user.dto.MultipleMediaRequest;
import com.pixogram.user.dto.ProducerMediaDTO;
import com.pixogram.user.dto.SingleMediaRequest;
import com.pixogram.user.entity.User;
import com.pixogram.user.repository.UserRepository;
import com.pixogram.user.service.client.ProducerClient;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
	private ProducerClient producerClient;

	@Autowired
	public void setProducerClient(ProducerClient producerClient) {
		this.producerClient = producerClient;
	}

	public List<ProducerMediaDTO> allMediaByUserId(Long userId) {
		return producerClient.allMediaByUserId(userId);
	}

	public String getMessageFromProducer() {
		return producerClient.getMessage();
	}

	public String singleFileUpload(SingleMediaRequest singleMediaRequest) {
		return producerClient.singleFileUpload(singleMediaRequest);
	}

	public String multipleFileUpload(MultipleMediaRequest multipleMediaRequest) {
		System.out.println("getDesc===>"+multipleMediaRequest.getMediaList().get(0).getDesc());
		//multipleMediaRequest.getMediaList().get(0).getFile().getOriginalFilename();
		return producerClient.multipleFileUpload(multipleMediaRequest);
	}

	public boolean verifyPerson(String userName, String password) {
		boolean isAuthenticated = false;
		User user = repo.findByUserNameAndPassword(userName, password);
		if (user != null) {
			isAuthenticated = true;
		}
		return isAuthenticated;
	}
}
