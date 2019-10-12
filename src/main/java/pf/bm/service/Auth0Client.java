package pf.bm.service;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.mgmt.users.User;
import com.auth0.json.mgmt.users.UsersPage;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;
import com.auth0.net.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pf.bm.config.Config;

import java.util.List;

@Service
public class Auth0Client {

    @Autowired
    private Config config;

    //Method to request JWT Token from Auth0
    public String getToken(String username, String password) throws Auth0Exception {
        AuthAPI authApi = new AuthAPI(config.getDomain(), config.getManagementClientId(), config.getManagementClientSecret());
        TokenRequest request = (TokenRequest) authApi.login(username, password);
        request.addParameter("responseType","token id_token");
        request.setScope(config.getLoginScope());
        TokenHolder tokenHolder = request.execute();
        return tokenHolder.getAccessToken();
    }

    //Method to get all users currently registered to Auth0
    public List<User> getAllUsers() throws Auth0Exception {
        AuthAPI authApi = new AuthAPI(config.getDomain(), config.getManagementClientId(), config.getManagementClientSecret());
        AuthRequest request = authApi.requestToken(config.getAudience());
        TokenHolder holder = request.execute();
        ManagementAPI managementAPI = new ManagementAPI(config.getDomain(), holder.getAccessToken());
        Request<UsersPage> users = managementAPI.users().list(null);
        return users.execute().getItems();
    }
}
