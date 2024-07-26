package job.testtask.minibank.controller;


import job.testtask.minibank.dto.UserSearchDto;
import job.testtask.minibank.entity.UserEntity;
import job.testtask.minibank.filter.FILTER_TYPE;
import job.testtask.minibank.service.UserService;
import job.testtask.minibank.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
@Import({JwtUtil.class})
public class UserControllerTest {


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @WithMockUser(username="user1@mail.ru")
    @Test
    public void givenDate_whenGetEmployeeById_thenReturnList() throws Exception {
        // given - precondition or setup
        final long employeeId = 1L;
        final UserSearchDto userSearchDto = new UserSearchDto("05.01.1991", FILTER_TYPE.DATE_OF_BIRTH, 2, 1);
        final List<UserEntity> users = new ArrayList<>();
        final UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(1L);
        final UserEntity userEntity2 = new UserEntity();
        userEntity2.setId(2L);
        users.add(userEntity1);
        users.add(userEntity2);
        given(userService.getUsers(userSearchDto)).willReturn(users);

        // when -  action or the behaviour that we are going test
        final ResultActions response = mockMvc.perform(myFactoryRequest("/user/getUsers?value=05.01.1991&filterType=DATE_OF_BIRTH&size= 2&page=1"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(users.size())));
    }

    public MockHttpServletRequestBuilder myFactoryRequest(final String url) {

        return MockMvcRequestBuilders.get(url);
//                .header("Authorization",
//                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQRVRSIFBFVFJPViIsInVzZXJfaWQiOiIyIiwiZXhwIjoxNjc0NDY3NTMyLCJpYXQiOjE2NzQ0MzE1MzJ9.5YVpGlg7aLStME95bPefQHhzECUbTL2sGeMYrSQlLb4")
//                .header("Content-Type","application/json");
    }
}