package com.iot.edge.consumer;

import com.iot.edge.service.AssetService;
import dto.SensorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class EdgeConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(EdgeConsumer.class.getName());

    private final AssetService service;
    private final Processor processor;

    public EdgeConsumer(AssetService service, Processor processor) {
        this.service = service;
        this.processor = processor;
    }

    @StreamListener(Processor.INPUT)
    public void handle(SensorData sensorData) {
        if (service.validateSensorData(sensorData)) {
            LOGGER.info("Sensor Data : {}", sensorData);
            processor.output().send(MessageBuilder.withPayload(sensorData).build());
        } else {
            LOGGER.info("Rejected Sensor Data : {}", sensorData);
        }


    }

}
