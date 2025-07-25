package org.jaros.atiperarecruitmenttask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubDTOBranch {

        private String name;
        private Commit commit;

        public GitHubDTOBranch( String name, Commit commit ) {
            this.name = name;
            this.commit = commit;
        }

        @Getter
        @Setter
        @ToString
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Commit {
            private String sha;

        }

}
