package pf.bm.service;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pf.bm.config.KafkaConsumerConfig;
import pf.bm.constants.SoaConstants;
import pf.bm.dto.UserJson;
import pf.bm.dto.UserListJson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pf.bm.constants.SoaConstants.TOPIC_USER_REQUEST;
import static pf.bm.constants.SoaConstants.TOPIC_USER_RESPONSE;

@Service
public class KafkaMessageProcessor {

    private Logger logger = LoggerFactory.getLogger(KafkaMessageProcessor.class);

    @Autowired
    private Auth0Client auth0Client;
    @Autowired
    private KafkaService kafkaService;

    @KafkaListener(topics = TOPIC_USER_REQUEST, groupId = KafkaConsumerConfig.USER_REQUEST_GROUP)
    public void listen(String message) throws Auth0Exception {
        System.out.println("Backend received message in group " + KafkaConsumerConfig.USER_REQUEST_GROUP + ": " + message);
        switch (message) {
            case SoaConstants.ACTION_GET_AUTH_USERS: {
                List<User> users = auth0Client.getAllUsers();
                kafkaService.sendMessage(TOPIC_USER_RESPONSE, buildUserListJsonResponse(users));
                break;
            }
            default: {
                kafkaService.sendMessage(TOPIC_USER_RESPONSE, buildUserListJsonResponse(new ArrayList<>()));
            }
        }
    }


    private UserListJson buildUserListJsonResponse(List<User> users) {
        UserListJson userListJson = new UserListJson();
        userListJson.users = users.stream().map(this::buildUserJson).collect(Collectors.toList());
        return userListJson;
    }

    private UserJson buildUserJson(User user) {
        UserJson userJson = new UserJson();
        userJson.email = user.getEmail();
        userJson.auth0Id = user.getId();
        userJson.nickname = user.getNickname();
        userJson.createdAt = user.getCreatedAt();
        userJson.updatedAt = user.getUpdatedAt();
        userJson.lastIp = user.getLastIP() != null ? user.getLastIP() : "none";
        userJson.lastLogin = user.getLastLogin();
        userJson.loginsCount = user.getLoginsCount() != null ? user.getLoginsCount() : 0;
        return userJson;
    }
}
