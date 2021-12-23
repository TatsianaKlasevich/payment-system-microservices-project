package com.klasevich.itrex.lab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.mapper.UserRequestDTOToUserMapper;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.klasevich.itrex.lab.util.TestData.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    public ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @MockBean
    private UserRequestDTOToUserMapper userRequestDTOToUserMapper;

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "read_user")
    void getUserById_whenValidUserData_thenStatus200AndValidUserReturn() throws Exception {
        //given
        User user = createNewUser();
        Long userId = user.getUserId();

        //when
        when(userService.getUserById(anyLong())).thenReturn(user);

        //then
        mockMvc.perform(get("/v1/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "create_user")
    void createUser_whenValidUserData_thenStatus200AndValidUserReturn() throws Exception {
        //given
        User user = createNewUser();
        UserRequestDTO userRequestDTO = createNewUserRequestDTO();

        //when
        when(userService.createUser(any())).thenReturn(user);

        //then
        mockMvc.perform(post("/v1/")
                        .content(objectMapper.writeValueAsString(userRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getUserId()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "update_user")
    void updateUser_whenValidUserData_thenStatus200AndValidUserReturn() throws Exception {
        //given
        User user = createNewUser();
        UserRequestDTO userRequestDTO = createNewUserRequestDTO();

        //when
        when(userService.updateUser(any())).thenReturn(user);
        when(userRequestDTOToUserMapper.convert(any())).thenReturn(user);

        //then
        mockMvc.perform(put("/v1/1")
                        .content(objectMapper.writeValueAsString(userRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getUserId()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "delete")
    void deleteUser_whenUserDeletedSuccessfully_thenStatus200Return() throws Exception {
        //given
        User user = createNewUser();

        //when
        when(userService.deleteUser(any())).thenReturn(user);

        //then
        mockMvc.perform(delete("/v1/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.getUserId()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = "read_all")
    void findAllUsersBySomePage_whenGetUsers_thenStatus200andValidUsersNumberReturn() throws Exception {
        //given
        User firstUser = createNewUser();
        User secondUser = createSecondUser();

        //when
        when(userService.findAllUsers(any())).thenReturn(new PageImpl<>(List.of(firstUser, secondUser)));

        //then
        mockMvc.perform(get("/v1/pageable?number=1&size=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));

    }
}