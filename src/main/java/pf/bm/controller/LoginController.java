package pf.bm.controller;

import com.auth0.exception.Auth0Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pf.bm.dto.LoginRequestJson;
import pf.bm.dto.TokenJson;
import pf.bm.service.Auth0Client;

@RestController
@RequestMapping(value = "/soa/api")
public class LoginController {

    @Autowired
    private Auth0Client auth0Client;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public TokenJson login(@RequestBody LoginRequestJson loginRequest) throws Auth0Exception {
        String token = auth0Client.getToken(loginRequest.username, loginRequest.password);

        return buildTokenJson(token);
    }

    private TokenJson buildTokenJson(String token) {
        TokenJson tokenJson = new TokenJson();
        tokenJson.token = token;
        return tokenJson;
    }
}
