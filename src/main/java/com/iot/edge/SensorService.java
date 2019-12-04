package com.iot.edge;

import org.influxdb.dto.Point;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SensorService {

    private final InfluxDBTemplate<Point> influxDBTemplate;

    public SensorService(InfluxDBTemplate<Point> influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

    public void write() {
        influxDBTemplate.createDatabase();
        final Point p = Point.measurement("disk")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("tenant", "default")
                .addField("used", 80L)
                .addField("free", 1L)
                .build();
        influxDBTemplate.write(p);

    }



}
