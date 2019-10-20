package pf.bm.service;

import com.auth0.json.mgmt.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaService {

    @Autowired(required = false)
    private KafkaTemplate<String, List<User>> kafkaUsersTemplate;
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaStringTemplate;

    public void sendMessage(String topicName, String msg) {
        kafkaStringTemplate.send(topicName, msg);
    }

    public void sendMessage(String topicName, List<User> msg) {
        kafkaUsersTemplate.send(topicName, msg);
    }
}
