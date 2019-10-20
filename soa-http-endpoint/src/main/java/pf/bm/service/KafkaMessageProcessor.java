package pf.bm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pf.bm.config.KafkaConsumerConfig;
import pf.bm.constants.SoaConstants;

import javax.annotation.PostConstruct;

@Service
public class KafkaMessageProcessor {

    @Autowired
    private KafkaService kafkaService;

    @KafkaListener(topics = SoaConstants.TOPIC_USER_RESPONSE, groupId = KafkaConsumerConfig.USER_RESPONSE_GROUP)
    public void listen(String message) {
        System.out.println("Endpoint received message in group " + KafkaConsumerConfig.USER_RESPONSE_GROUP + ": " + message);
    }

    @PostConstruct
    public void setUp() {
        kafkaService.sendMessage(SoaConstants.TOPIC_USER_REQUEST,"soa-endpoint is up an running");
    }
}
