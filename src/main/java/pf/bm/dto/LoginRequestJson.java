package pf.bm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequestJson {

    @JsonProperty
    public String username;

    @JsonProperty
    public String password;
}
