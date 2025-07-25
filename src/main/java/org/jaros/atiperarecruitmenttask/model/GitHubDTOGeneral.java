package org.jaros.atiperarecruitmenttask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class GitHubDTOGeneral {
    @JsonIgnore
    private int id;
    private String name;
    private Owner owner;
    private List<GitHubDTOBranch> branch;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean fork;

    public GitHubDTOGeneral(int id, String name, Owner owner, Boolean fork) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.fork = fork;

    }


    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner{
        private String login;

        public Owner(String login) {
            this.login = login;
        }
    }


}
