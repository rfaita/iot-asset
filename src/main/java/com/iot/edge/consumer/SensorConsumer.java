package com.iot.edge.consumer;

import com.iot.edge.SensorService;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class SensorConsumer {

    private final SensorService sensorService;

    public SensorConsumer(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @StreamListener(Sink.INPUT)
    public void handle(String message) {
        System.out.println(message);

        this.sensorService.write();
    }

}
