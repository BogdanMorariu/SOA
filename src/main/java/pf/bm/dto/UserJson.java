package pf.bm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserJson {

    @JsonProperty
    public String email;
    @JsonProperty
    public String auth0Id;
    @JsonProperty
    public String nickname;
    @JsonProperty
    public Date createdAt;
    @JsonProperty
    public Date updatedAt;
    @JsonProperty
    public String lastIp;
    @JsonProperty
    public Date lastLogin;
    @JsonProperty
    public int loginsCount;
}
