package com.cspinformatique.redis.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.redis.server.service.MessageService;


@Controller
@RequestMapping("/")
public class MessageController {
	@Autowired private MessageService messageService;
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method=RequestMethod.POST)
	public void saveMessage(@RequestBody String message){
		messageService.publishMessage(message);
	}
}
