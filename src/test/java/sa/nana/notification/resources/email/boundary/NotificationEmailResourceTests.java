package sa.nana.notification.resources.email.boundary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sa.nana.notification.Utilities;
import sa.nana.notification.models.EmailModel;
import sa.nana.notification.resources.email.entity.EmailPayload;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NotificationEmailResourceTests {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void sentEmail() throws Exception {
        EmailPayload payload = new EmailPayload();
        EmailModel model = new EmailModel();
        model.setSubject("Test email");
        model.setFrom("amawaziny@gmail.com");
        model.setTo("amawaziny@gmail.com");
        model.setCc("amawaziny@gmail.com");
        model.setBody("Body for test email");
        payload.setEmail(model);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/email/")
                .contentType(MediaType.APPLICATION_JSON).content(Utilities.mapToJson(payload))).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(202, response.getStatus());
    }

    @Test
    void sentEmailToIsNull() throws Exception {
        EmailPayload payload = new EmailPayload();
        EmailModel model = new EmailModel();
        model.setSubject("Test email");
        model.setFrom("amawaziny@gmail.com");
        model.setCc("amawaziny@gmail.com");
        model.setBody("Body for test email");
        payload.setEmail(model);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/email/")
                .contentType(MediaType.APPLICATION_JSON).content(Utilities.mapToJson(payload))).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }
}