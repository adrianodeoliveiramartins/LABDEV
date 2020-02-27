package br.edu.ifrs.canoas.lds.webapp.controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import br.edu.ifrs.canoas.lds.webapp.config.Messages;
import br.edu.ifrs.canoas.lds.webapp.config.auth.UserImpl;
import br.edu.ifrs.canoas.lds.webapp.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest extends BaseControllerTest{

    // All autowired fields from Controller must have a mock
    @MockBean
    Messages messages;
    @MockBean
    UserService userService;
    @MockBean
    UserImpl userImpl;

    @Test
    public void view_user_profile() throws Exception{
    	given(userService.getOne(super.user)).willReturn(super.user);
        this.mvc.perform(get("/user/profile")
                .with(user(userDetails))
                .accept(MediaType.TEXT_HTML)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("/user/profile"))
                .andExpect(model().attribute("user"
                        ,allOf(
                                hasProperty("id", is(USER_ID))
                                ,hasProperty("name", is(USER_NAME)))
                ))
        ;
    }

    @Test
    public void save_user_profile() throws Exception{
    	given(userService.getOne(super.user)).willReturn(super.user);
        given(this.messages.get("field.saved")).willReturn(FIELD_SAVED);

        this.mvc.perform(post("/user/save")
                .with(user(userDetails))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", USER_ID.toString())
                .param("name", USER_NAME)
                .param("email", USER_EMAIL)
        )
                .andExpect(view().name("redirect:/user/profile"))
                .andExpect(model().size(1))
                .andExpect(flash().attributeExists("message"))
                .andExpect(flash().attribute("message", FIELD_SAVED))
        ;
    }



}