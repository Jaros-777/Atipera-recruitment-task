package org.jaros.atiperarecruitmenttask.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class GitHubControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGetCorrectRepository() throws Exception {
        mvc.perform(get("/repo/Jaros-777"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("ATM"))
                .andExpect(jsonPath("$[0].owner.login").value("Jaros-777"))
                .andExpect(jsonPath("$[0].branch[0].name").value("main"))
                .andExpect(jsonPath("$[0].branch[0].commit.sha").value("6ad1232d7701a761266315a3343ab8f6fc5281c2"));
    }
}