package com.klasevich.itrex.lab.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test") //todo
@SpringBootTest
@AutoConfigureWireMock(port = 9091)
class UserServiceClientTest {

    @Autowired
    UserServiceClient userServiceClient;
//
//    @Test
//    void getUserById_whenValidClient_returnValidResponse() throws IOException {
//        stubFor(get(urlEqualTo("/users/v1/1"))
//                .willReturn(aResponse()
//                        .withStatus(HttpStatus.OK.value())
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody(read("stubs/users/v1/1.json"))));
//
//        UserResponseDTO userResponseDTO = userServiceClient.getUserById(1L);
//
//        assertThat(userResponseDTO.getEmail()).isEqualTo("klasevich.t@gmail.com");
//    }
//
//    private String read(String location) throws IOException {
//        return IOUtils.toString(new ClassPathResource(location).getInputStream(), String.valueOf(StandardCharsets.UTF_8));
//    }
}
