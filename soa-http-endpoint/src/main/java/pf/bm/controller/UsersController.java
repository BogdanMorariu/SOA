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

    @RequestMapping(method = RequestMethod.GET)
    public UserListJson test(TokenJson tokenJson) throws Auth0Exception {
        tokenValidator.validateToken(tokenJson);
        kafkaService.sendMessage(SoaConstants.GET_AUTH_USERS);

        return null;
        //return buildUserListJsonResponse(users);
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
