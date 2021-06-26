package sa.nana.notification.receivers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import sa.nana.notification.models.FailureNotificationPayload;
import sa.nana.notification.resources.sms.entity.SMSPayload;

import javax.validation.Valid;

import static sa.nana.notification.configurations.Constants.QUEUE_SMS;
import static sa.nana.notification.configurations.Constants.QUEUE_SMS_FAILED;

@Slf4j
@Service
public class SMSNotificationReceiver {

    private final JmsTemplate jms;

    public SMSNotificationReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    @JmsListener(destination = QUEUE_SMS, containerFactory = "notificationJMSFactory")
    public void receive(@Valid @Payload SMSPayload payload) {
        try {
            log.debug("[SMS MESSAGE] Received: {}", payload);
        } catch (Exception e) {
            jms.convertAndSend(QUEUE_SMS_FAILED, new FailureNotificationPayload(e.getMessage(), payload));
        }
    }
}
