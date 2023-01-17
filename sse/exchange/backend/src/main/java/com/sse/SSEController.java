package com.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class SSEController {
    private static Logger logger = LoggerFactory.getLogger(SSEController.class);
    @GetMapping("/hello-world")
    public ResponseEntity<String> get() {
        logger.info ("Inside hello method");
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("stream-sse-mvc")
    public SseEmitter streamSseMvc() {
        logger.info ("Inside streamSseMvc method");
        SseEmitter emitter = new SseEmitter (Long.MAX_VALUE);
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor ();
        sseMvcExecutor.execute (() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event ()
                            .data ("Sample Data.. " + LocalTime.now ().toString ())
                            .id (String.valueOf (i))
                            .name ("message");
                    emitter.send (event);
                    System.out.println ("Event with id sent..." + i);
                    Thread.sleep (1000);
                }
            } catch (Exception ex) {
                System.out.println ("EventSource failed.." + ex);
                emitter.completeWithError (ex);
            }
        });
        sseMvcExecutor.shutdown ();
        return emitter;
    }


//	@GetMapping("/stream-sse-mvc")
//	public SseEmitter streamSseMvc() {
//		SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
//		ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
//		sseMvcExecutor.execute(() -> {
//					List<DataSet> dataSets = dataSetService.findAll();
//					try {
//						for (DataSet dataSet : dataSets) {
//
//							randomDelay();
//							emitter.send(dataSet);
//						}
//
//						emitter.complete();
//					}
//			catch (Exception ex) {
//				logger.error("EventSource failed..", ex);
//				emitter.completeWithError(ex);
//			}
//		});
//		sseMvcExecutor.shutdown();
//		return emitter;
//	}

//	@GetMapping("/stream-sse-mvc")
//	public SseEmitter streamSseMvc(HttpServletResponse response) {
//		response.setHeader("Cache-Control", "no-store");
//		SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
//		this.emitters.add(emitter);
//		emitter.onCompletion(() -> this.emitters.remove(emitter));
//		emitter.onTimeout(() -> this.emitters.remove(emitter));
//		randomDelay();
//		return emitter;
//	}

    //	@PostMapping("/notify")
//	public void postMessage(@RequestParam  String message) {
//		for (SseEmitter emitter : emitters) {
//			try {
//				logger.info("Sending message.. {}",message);
//				randomDelay();
//				emitter.send(SseEmitter.event().id("100").name("message").data(message, MediaType.ALL));
//				logger.info("Sent message.. {}",message);
//				emitter.complete();
//			} catch (IOException e) {
//				e.printStackTrace();
//				this.emitters.remove(emitter);
//			}
//		}
//	}
//
    private void randomDelay() {
        try {
            Thread.sleep (1000);
        } catch (InterruptedException e) {
            Thread.currentThread ().interrupt ();
        }
    }

	/*@GetMapping("/stream-sse-mvc")
	public SseEmitter handle(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-store");

		SseEmitter emitter = new SseEmitter(180_000L);
		// SseEmitter emitter = new SseEmitter(180_000L);

		this.emitters.add(emitter);

		emitter.onCompletion(() -> this.emitters.remove(emitter));
		emitter.onTimeout(() -> this.emitters.remove(emitter));

		return emitter;
	}

	@EventListener
	public void onDataInput(String data) {
		List<SseEmitter> deadEmitters = new ArrayList<>();
		this.emitters.forEach(emitter -> {
			try {
				SseEmitter.SseEventBuilder event = SseEmitter.event()
						.data("SSE MVC - ?MNMONTANA POWER CO.RR - " + data)
						.name("sse event - mvc");
				emitter.send(event);
				logger.info("Event sent...");

				// close connnection, browser automatically reconnects
				// emitter.complete();

				// SseEventBuilder builder = SseEmitter.event().name("second").data("1");
				// SseEventBuilder builder =
				// SseEmitter.event().reconnectTime(10_000L).data(memoryInfo).id("1");
				// emitter.send(builder);
			}
			catch (Exception e) {
				deadEmitters.add(emitter);
			}
		});

		this.emitters.removeAll(deadEmitters);
	}*/
}
