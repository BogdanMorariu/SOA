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
import pf.bm.config.Auth0Config;

import java.util.List;

@Service
public class Auth0Client {

    @Autowired
    private Auth0Config auth0Config;

    //Method to get all users currently registered to Auth0
    public List<User> getAllUsers() throws Auth0Exception {
        AuthAPI authApi = new AuthAPI(auth0Config.getDomain(), auth0Config.getManagementClientId(), auth0Config.getManagementClientSecret());
        AuthRequest request = authApi.requestToken(auth0Config.getAudience());
        TokenHolder holder = request.execute();
        ManagementAPI managementAPI = new ManagementAPI(auth0Config.getDomain(), holder.getAccessToken());
        Request<UsersPage> users = managementAPI.users().list(null);
        return users.execute().getItems();
    }
}
