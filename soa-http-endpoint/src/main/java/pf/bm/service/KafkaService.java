package pf.bm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String topicName = "user-request";

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }

    @PostConstruct
    private void setUp() {
        sendMessage("endpoint is up and running");
    }
}
