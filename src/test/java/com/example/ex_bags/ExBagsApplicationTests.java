package com.example.ex_bags;

import com.example.ex_bags.Models.Post;
import com.example.ex_bags.Repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ExBagsApplicationTests {
    @MockBean
    PostRepository postRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    DataSource dataSource;
    @Test
    void PostAdd() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setName("Продавец");
        post.setSalary(10000);
        Mockito.when(postRepository.save(post)).thenReturn(Optional.of(post).orElseThrow());
        mockMvc.perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk());
    }

    @Test
    void PostFailed() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setName("П");
        post.setSalary(null);
        Mockito.when(postRepository.save(post)).thenReturn(Optional.of(post).orElseThrow());
        mockMvc.perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenPost_whenGetPost_thenStatus200()
            throws Exception {
        mockMvc.perform(get("/api/post/view")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

}
