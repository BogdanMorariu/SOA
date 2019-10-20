package pf.bm.service;

import com.auth0.json.mgmt.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pf.bm.config.KafkaConsumerConfig;
import pf.bm.constants.SoaConstants;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageProcessor {

    private Logger logger = LoggerFactory.getLogger(KafkaMessageProcessor.class);

    private List<User> usersResponse;

    @KafkaListener(topics = SoaConstants.TOPIC_USER_RESPONSE, groupId = KafkaConsumerConfig.USER_RESPONSE_GROUP)
    public void listen(String message) {
        logger.info("Endpoint received message in group " + KafkaConsumerConfig.USER_RESPONSE_GROUP + ": " + message);
        usersResponse = new ArrayList<>();
    }

    public List<User> getUsersResponse() {
        return usersResponse;
    }
}
