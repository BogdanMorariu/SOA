package pf.bm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pf.bm.dto.UserListJson;

@Service
public class KafkaService {

    @Autowired(required = false)
    private KafkaTemplate<String, UserListJson> kafkaUsersTemplate;
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaStringTemplate;

    public void sendMessage(String topicName, String msg) {
        kafkaStringTemplate.send(topicName, msg);
    }

    public void sendMessage(String topicName, UserListJson msg) {
        kafkaUsersTemplate.send(topicName, msg);
    }
}
