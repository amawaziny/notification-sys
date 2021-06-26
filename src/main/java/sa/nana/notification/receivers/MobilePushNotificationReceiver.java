package sa.nana.notification.receivers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import sa.nana.notification.models.FailureNotificationPayload;
import sa.nana.notification.resources.mobile.entity.MobilePushPayload;

import javax.validation.Valid;

import static sa.nana.notification.configurations.Constants.QUEUE_MOBILE;
import static sa.nana.notification.configurations.Constants.QUEUE_MOBILE_FAILED;

@Slf4j
@Service
public class MobilePushNotificationReceiver {

    private final JmsTemplate jms;

    public MobilePushNotificationReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    @JmsListener(destination = QUEUE_MOBILE, containerFactory = "notificationJMSFactory")
    public void receive(@Valid @Payload MobilePushPayload payload) {
        try {
            log.debug("[MOBILE PUSH MESSAGE] Received: {}", payload);
        } catch (Exception e) {
            jms.convertAndSend(QUEUE_MOBILE_FAILED, new FailureNotificationPayload(e.getMessage(), payload));
        }
    }
}
