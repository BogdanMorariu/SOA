package pf.bm.service;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pf.bm.config.Auth0Config;

@Service
public class Auth0Client {

    @Autowired
    private Auth0Config auth0Config;

    //Method to request JWT Token from Auth0
    public String getToken(String username, String password) throws Auth0Exception {
        AuthAPI authApi = new AuthAPI(auth0Config.getDomain(), auth0Config.getManagementClientId(), auth0Config.getManagementClientSecret());
        TokenRequest request = (TokenRequest) authApi.login(username, password);
        request.addParameter("responseType","token id_token");
        request.setScope(auth0Config.getLoginScope());
        TokenHolder tokenHolder = request.execute();
        return tokenHolder.getAccessToken();
    }
}
