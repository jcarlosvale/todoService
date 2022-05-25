package com.study;

import com.study.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoServiceApplicationTest {

    private RestTemplate restTemplate = new RestTemplate();
    private String url;

    @LocalServerPort
    private int randomServerPort = 0;

    @BeforeEach
    void beforeTest() {
        url = "http://localhost:" + randomServerPort;
    }

    @Test
    void getAll() {

        //GIVEN
        url += "/user";

        //WHEN
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity(url, UserDto[].class);

        //THEN

        UserDto[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assert body != null;
        assertThat(body).hasSize(1);
        assertThat(body[0]).isEqualTo(UserDto.builder().username("teste").name("nome teste").build());



    }
}