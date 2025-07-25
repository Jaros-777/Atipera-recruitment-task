package org.jaros.atiperarecruitmenttask.controller;

import org.jaros.atiperarecruitmenttask.service.GitHubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repo")
public class GitHubController {

    GitHubService gitHubService;

    GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> repo(@PathVariable("username") String userName) {

        return gitHubService.getGitHubDetails(userName);

    }
}
