package by.strizhonov.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        AppExceptionHandler.class,
        AppExceptionHandlerTest.ExceptionThrowingController.class})
public class AppExceptionHandlerTest {


    private static final String EXCEPTION_PATH = "/exception";
    private static final String HTTP_MESSAGE_NOT_READABLE_EXCEPTION_PATH = "/http_message_not_readable_exception";
    private static final String CONSTRAINT_VIOLATION_EXCEPTION_PATH = "/constraint_violation_exception";
    private static final String ENTITY_NOT_FOUND_EXCEPTION_PATH = "/entity_not_found_exception";
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;


    @Test
    public void shouldHandleEntityNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ENTITY_NOT_FOUND_EXCEPTION_PATH))
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldHandleConstraintViolationException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONSTRAINT_VIOLATION_EXCEPTION_PATH))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldHandleHttpMessageNotReadableException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(HTTP_MESSAGE_NOT_READABLE_EXCEPTION_PATH))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldHandleException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(EXCEPTION_PATH))
                .andExpect(status().isInternalServerError());
    }


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }


    @Controller
    @EnableWebMvc
    public static class ExceptionThrowingController {

        @RequestMapping(value = ENTITY_NOT_FOUND_EXCEPTION_PATH, method = GET)
        public @ResponseBody
        String throwEntityNotFoundException() {
            throw new EntityNotFoundException("test_exception");
        }

        @SuppressWarnings("all")
        @RequestMapping(value = HTTP_MESSAGE_NOT_READABLE_EXCEPTION_PATH, method = GET)
        public @ResponseBody
        String throwHttpMessageNotReadableException() {
            throw new HttpMessageNotReadableException("test_exception", (HttpInputMessage) null);
        }

        @RequestMapping(value = CONSTRAINT_VIOLATION_EXCEPTION_PATH, method = GET)
        public @ResponseBody
        String throwConstraintViolationException() {
            throw new ConstraintViolationException(new HashSet<>());
        }

        @RequestMapping(value = EXCEPTION_PATH, method = GET)
        public @ResponseBody
        String throwException() throws Exception {
            throw new Exception("test_exception");
        }
    }


}
