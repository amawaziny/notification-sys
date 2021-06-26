package sa.nana.notification.resources.sms.boundary;

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
import sa.nana.notification.models.PhoneNumber;
import sa.nana.notification.models.SMSModel;
import sa.nana.notification.resources.sms.entity.SMSPayload;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NotificationSmsResourceTests {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void sendSMS() throws Exception {
        SMSPayload payload = new SMSPayload();
        SMSModel model = new SMSModel();
        model.setTo(new PhoneNumber("+201003009331", "EG"));
        model.setBody("Body sms");
        payload.setSms(model);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/sms/")
                .contentType(MediaType.APPLICATION_JSON).content(Utilities.mapToJson(payload))).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(202, response.getStatus());
    }

    @Test
    void sendSMSBodyIsNull() throws Exception {
        SMSPayload payload = new SMSPayload();
        SMSModel model = new SMSModel();
        model.setBody("Body sms");
        payload.setSms(model);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/sms/")
                .contentType(MediaType.APPLICATION_JSON).content(Utilities.mapToJson(payload))).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void sendSMSWrongPhoneNumber() throws Exception {
        SMSPayload payload = new SMSPayload();
        SMSModel model = new SMSModel();
        model.setTo(new PhoneNumber("0030093311", "EG"));
        model.setBody("Body sms");
        payload.setSms(model);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/sms/")
                .contentType(MediaType.APPLICATION_JSON).content(Utilities.mapToJson(payload))).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }
}