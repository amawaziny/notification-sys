package sa.nana.notification.resources.email.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.nana.notification.resources.advice.entity.ResponseMessage;
import sa.nana.notification.resources.email.entity.EmailPayload;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static sa.nana.notification.configurations.Constants.QUEUE_EMAIL;

@Slf4j
@RestController
@RequestMapping(value = "/email", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class NotificationEmailResource {

    private final JmsTemplate jms;

    @Autowired
    public NotificationEmailResource(JmsTemplate jms) {
        this.jms = jms;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> sentEmail(@Valid @RequestBody EmailPayload payload) {
        log.debug("[EMAIL REQUEST] Received: {}", payload);

        if (payload.getDelay() > 0) {
            jms.setDeliveryDelay(payload.getDelay());
        }

        try {
            jms.convertAndSend(QUEUE_EMAIL, payload);
            return ResponseEntity.accepted().body(new ResponseMessage("Message sent"));
        } catch (Exception ex) {
            return ResponseEntity.status(GATEWAY_TIMEOUT).
                    body(new ResponseMessage(ex.getCause().getCause().getMessage()));
        }
    }
}
