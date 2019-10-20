package pf.bm.controller;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pf.bm.constants.SoaConstants;
import pf.bm.dto.TokenJson;
import pf.bm.dto.UserJson;
import pf.bm.dto.UserListJson;
import pf.bm.service.Auth0Client;
import pf.bm.service.KafkaMessageProcessor;
import pf.bm.service.KafkaService;
import pf.bm.validator.TokenValidator;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/soa/api/users")
public class UsersController {

    @Autowired
    private Auth0Client auth0Client;
    @Autowired
    private TokenValidator tokenValidator;
    @Autowired
    private KafkaService kafkaService;
    @Autowired
    private KafkaMessageProcessor kafkaMessageProcessor;

    @RequestMapping(method = RequestMethod.GET)
    public UserListJson getAllUsers(TokenJson tokenJson) throws InterruptedException {
        tokenValidator.validateToken(tokenJson);
        kafkaService.sendMessage(SoaConstants.TOPIC_USER_REQUEST, SoaConstants.ACTION_GET_AUTH_USERS);
        Thread.sleep(5000);

        return kafkaMessageProcessor.getUsersResponse();
    }
}
