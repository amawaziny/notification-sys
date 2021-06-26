package sa.nana.notification.resources.mobile.boundary;

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
import sa.nana.notification.models.MobilePushModel;
import sa.nana.notification.models.OsType;
import sa.nana.notification.resources.mobile.entity.MobilePushPayload;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NotificationMobilePushResourceTests {
    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void sendMobile() throws Exception {
        MobilePushPayload payload = new MobilePushPayload();
        MobilePushModel mobilePush = new MobilePushModel();
        mobilePush.setOs(OsType.ANDROID);
        mobilePush.setBody("Hello Android users");
        payload.setMobilePush(mobilePush);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/mobile-push/")
                .contentType(MediaType.APPLICATION_JSON).content(Utilities.mapToJson(payload))).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(202, response.getStatus());
    }

    @Test
    void sendMobileBodyIsNull() throws Exception {
        MobilePushPayload payload = new MobilePushPayload();
        MobilePushModel mobilePush = new MobilePushModel();
        mobilePush.setOs(OsType.ANDROID);
        payload.setMobilePush(mobilePush);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/mobile-push/")
                .contentType(MediaType.APPLICATION_JSON).content(Utilities.mapToJson(payload))).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }
}