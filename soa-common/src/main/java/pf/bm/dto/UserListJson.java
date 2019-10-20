package pf.bm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class UserListJson {

    @JsonProperty
    public List<UserJson> users;

    @Override
    public UserListJson clone(){
        UserListJson newUserList = new UserListJson();
        newUserList.users = users.stream().map(UserJson::clone).collect(Collectors.toList());
        return newUserList;
    }
}
