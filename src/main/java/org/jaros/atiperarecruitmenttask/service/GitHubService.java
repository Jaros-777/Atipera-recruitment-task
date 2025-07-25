package org.jaros.atiperarecruitmenttask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jaros.atiperarecruitmenttask.model.ApiError;
import org.jaros.atiperarecruitmenttask.model.GitHubDTOBranch;
import org.jaros.atiperarecruitmenttask.model.GitHubDTOGeneral;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GitHubService {

    private final RestTemplate restTemplate;

    public GitHubService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ResponseEntity<?> getGitHubDetails(String userName) {
        String userUrl = "https://api.github.com/users/" + userName + "/repos";

        try{

            ResponseEntity<?> gitHubUserResponse = restTemplate.exchange(userUrl, HttpMethod.GET, null , String.class);
            ObjectMapper mapper = new ObjectMapper();
            List<GitHubDTOGeneral> GitHubGeneralsList = mapper.readValue((String) gitHubUserResponse.getBody(), new TypeReference<List<GitHubDTOGeneral>>() {});


            for(GitHubDTOGeneral repo : GitHubGeneralsList){
                if(!repo.getFork()){
                    String branchUrl = "https://api.github.com/repos/" + userName + "/" + repo.getName() + "/branches";
                    ResponseEntity<?> gitHubBranchResponse = restTemplate.exchange(branchUrl, HttpMethod.GET, null , String.class);
                    List<GitHubDTOBranch> GitHubBranchList = mapper.readValue((String) gitHubBranchResponse.getBody(), new TypeReference<List<GitHubDTOBranch>>() {});
                    repo.setBranch(GitHubBranchList);
                }

            }


            return ResponseEntity.ok(GitHubGeneralsList);

        }catch (HttpClientErrorException.NotFound e){
            ApiError error = new ApiError(404, "GitHub user '" + userName + "' not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }catch (HttpClientErrorException.Forbidden e) {
            ApiError error = new ApiError(403, "Rate limit exceeded. Use authenticated requests.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
