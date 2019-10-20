package pf.bm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserListJson {

    @JsonProperty
    public List<UserJson> users;
}
