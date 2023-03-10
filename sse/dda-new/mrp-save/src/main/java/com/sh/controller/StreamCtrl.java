package com.sh.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sh.events.CustomerEvent;
import com.sh.events.ShopEvent;

@Controller
public class StreamCtrl {
	
	private List<SseEmitter> lsEmitters = new ArrayList<SseEmitter>();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamCtrl.class);
	
	@RequestMapping("/stream.action")
	public SseEmitter stream()
	{
		SseEmitter emitter = new SseEmitter();
		lsEmitters.add(emitter);
		
		emitter.onCompletion(()->lsEmitters.remove(emitter));
		emitter.onTimeout(()->lsEmitters.remove(emitter));
		
		
		return emitter;
	}
	
	@EventListener({CustomerEvent.class})
	public void handleCustomerEvt(CustomerEvent evt)
	{
		System.out.println("EVENT RECEIVED: "+evt.getMsg().getCustName());
		
		List<SseEmitter> deadEmitters = new ArrayList<SseEmitter>();
	    this.lsEmitters.forEach(emitter -> {
	      try {
	        emitter.send(evt.getMsg());
	      }
	      catch (Exception e) {
	    	  LOGGER.error("Error ",e);
	        deadEmitters.add(emitter);
	      }
	    });

	    this.lsEmitters.removeAll(deadEmitters);
	}
	
	@EventListener({ShopEvent.class})
	public void handleShopEvt(ShopEvent evt)
	{
		System.out.println("EVENT RECEIVED: "+evt.getMsg().getShopName());
		
		List<SseEmitter> deadEmitters = new ArrayList<SseEmitter>();
	    this.lsEmitters.forEach(emitter -> {
	      try {
	        emitter.send(evt.getMsg());
	      }
	      catch (Exception e) {
	    	  LOGGER.error("Error ",e);
	        deadEmitters.add(emitter);
	      }
	    });

	    this.lsEmitters.removeAll(deadEmitters);
	}

}
