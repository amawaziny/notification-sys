package sa.nana.notification.resources.sms.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.nana.notification.resources.advice.entity.ResponseMessage;
import sa.nana.notification.resources.sms.entity.SMSPayload;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static sa.nana.notification.configurations.Constants.QUEUE_SMS;

@Slf4j
@RestController
@RequestMapping(value = "/sms", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class NotificationSmsResource {

    private final JmsTemplate jms;

    @Autowired
    public NotificationSmsResource(JmsTemplate jms) {
        this.jms = jms;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> sendSMS(@Valid @RequestBody SMSPayload payload) {
        log.debug("[SMS REQUEST] Received: {}", payload);

        if (payload.getDelay() > 0) {
            jms.setDeliveryDelay(payload.getDelay());
        }

        try {
            jms.convertAndSend(QUEUE_SMS, payload);
            return ResponseEntity.accepted().body(new ResponseMessage("Message sent"));
        } catch (Exception ex) {
            return ResponseEntity.status(GATEWAY_TIMEOUT).
                    body(new ResponseMessage(ex.getCause().getCause().getMessage()));
        }
    }
}
