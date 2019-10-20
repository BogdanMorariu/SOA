package pf.bm.service;

import com.auth0.json.mgmt.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pf.bm.config.KafkaConsumerConfig;
import pf.bm.constants.SoaConstants;
import pf.bm.dto.UserListJson;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageProcessor {

    private Logger logger = LoggerFactory.getLogger(KafkaMessageProcessor.class);

    private UserListJson usersResponse;

    @KafkaListener(topics = SoaConstants.TOPIC_USER_RESPONSE, groupId = KafkaConsumerConfig.USER_RESPONSE_GROUP)
    public void listen(UserListJson message) {
        logger.info("Endpoint received message in group " + KafkaConsumerConfig.USER_RESPONSE_GROUP + ": " + message);
        usersResponse = message;
    }

    public UserListJson getUsersResponse() throws InterruptedException {
        if(usersResponse == null)
            Thread.sleep(500);
        UserListJson result = usersResponse.clone();
        usersResponse = null;
        return result;
    }
}
