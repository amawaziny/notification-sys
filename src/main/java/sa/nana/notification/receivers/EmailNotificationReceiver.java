package sa.nana.notification.receivers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import sa.nana.notification.models.FailureNotificationPayload;
import sa.nana.notification.resources.email.entity.EmailPayload;

import javax.validation.Valid;

import static sa.nana.notification.configurations.Constants.QUEUE_EMAIL;
import static sa.nana.notification.configurations.Constants.QUEUE_EMAIL_FAILED;

@Slf4j
@Service
public class EmailNotificationReceiver {

    private final JmsTemplate jms;

    public EmailNotificationReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    @JmsListener(destination = QUEUE_EMAIL, containerFactory = "notificationJMSFactory")
    public void receive(@Valid @Payload EmailPayload payload) {
        try {
            log.debug("[EMAIL MESSAGE] Received: {}", payload);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            jms.convertAndSend(QUEUE_EMAIL_FAILED, new FailureNotificationPayload(e.getMessage(), payload));
        }
    }
}
