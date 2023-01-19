package com.coffeeandcode.amqp.consumerapi;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class MachineController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public ResponseEntity<Machine> save(@RequestBody Machine machine) {
        Machine newMachine = new Machine("test1", "test2", "test3");
        System.out.println(String.format("Sending Machine: %s", newMachine.toString()));
        sendMachineToRabbit(newMachine);
        System.out.println(String.format("Machine sent: %s", newMachine.toString()));
        return ResponseEntity.ok(newMachine);
    }
    
    private void sendMachineToRabbit(Machine machine) {
        try {
            String json = new ObjectMapper().writeValueAsString(machine);
            rabbitTemplate.convertAndSend(MachineAMQPConfig.EXCHANGE_NAME, "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
