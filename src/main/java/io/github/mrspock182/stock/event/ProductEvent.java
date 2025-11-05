package io.github.mrspock182.stock.event;

import io.github.mrspock182.stock.event.adapter.ProductEventAdapter;
import io.github.mrspock182.stock.event.dto.EventProductProducer;
import io.github.mrspock182.stock.repository.orm.ProductOrm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class ProductEvent {
    private static final Logger LOG = LoggerFactory.getLogger(ProductEvent.class);

    private final String topic;
    private final KafkaTemplate<String, EventProductProducer> kafkaTemplate;

    public ProductEvent(
            KafkaTemplate<String, EventProductProducer> kafkaTemplate,
            @Value("${spring.kafka.topic.product.update}") String topic) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ProductOrm orm) {
        try {
            EventProductProducer produce = ProductEventAdapter.cast(orm);
            kafkaTemplate.send(topic, orm.getId(), produce)
                    .get(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            LOG.error("Error to send order event {}", orm, ex);
        }
    }
}
