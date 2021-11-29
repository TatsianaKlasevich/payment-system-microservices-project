package com.klasevich.itrex.lab.feign;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@AutoConfigureWireMock(port = 9091)
class UserServiceClientTest {

    @Autowired
    UserServiceClient userServiceClient;

//    @Test
//    void getValid_whenValidClient_returnValidResponse() {
//        stubFor(get(urlEqualTo("/users/v1/{userId}"))
//                .willReturn(aResponse()
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody(read("stubs/users/v1/1.json"))));
//
//        List<Post> posts = postFeignClient.getAllPosts();
//        Post post = posts.get(0);
//
//        // We're asserting if WireMock responded properly
//        assertThat(posts).hasSize(10);
//        assertThat(post.getId()).isEqualTo(1);
//        assertThat(post.getUserId()).isEqualTo(1);
//        assertThat(post.getTitle()).isEqualTo("title");
//        assertThat(post.getBody()).isEqualTo("body");
//    }
//
//    private String read(String location) throws IOException {
//        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
//    }
}
