package com.pixogram.user.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pixogram.user.dto.MultipleMediaRequest;
import com.pixogram.user.dto.ProducerMediaDTO;
import com.pixogram.user.dto.SingleMediaRequest;

@FeignClient(name = "spring-feign-producer")
public interface ProducerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/producer")
    String getMessage();

    @RequestMapping(method = RequestMethod.GET,value = "/producer/media/allMediaByUserId/{userId}")
    List<ProducerMediaDTO> allMediaByUserId(@PathVariable("userId") Long userId);

    @RequestMapping(method = RequestMethod.POST, value = "/producer/media/singleFileUpload")
    String singleFileUpload(@ModelAttribute SingleMediaRequest singleMediaRequest);
    
    @RequestMapping(method = RequestMethod.POST, value = "/producer/media/multipleFileUpload")
    String multipleFileUpload(@ModelAttribute MultipleMediaRequest multipleMediaRequest);
    
	/*
	 * @Configuration public class MultipartSupportConfig {
	 * 
	 * @Bean
	 * 
	 * @Primary
	 * 
	 * @Scope("prototype") public feign.codec.Encoder feignFormEncoder() { return
	 * new SpringFormEncoder(); } }
	 */
}
