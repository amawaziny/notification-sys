package sa.nana.notification.resources.mobile.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.nana.notification.resources.advice.entity.ResponseMessage;
import sa.nana.notification.resources.mobile.entity.MobilePushPayload;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static sa.nana.notification.configurations.Constants.QUEUE_MOBILE;

@Slf4j
@RestController
@RequestMapping(value = "/mobile-push", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class NotificationMobilePushResource {

    private final JmsTemplate jms;

    @Autowired
    public NotificationMobilePushResource(JmsTemplate jms) {
        this.jms = jms;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseMessage> sendMobile(@Valid @RequestBody MobilePushPayload payload) {
        log.debug("[MOBILE PUSH REQUEST] Received: {}", payload);

        if (payload.getDelay() > 0) {
            jms.setDeliveryDelay(payload.getDelay());
        }

        try {
            jms.convertAndSend(QUEUE_MOBILE, payload);
            return ResponseEntity.accepted().body(new ResponseMessage("Message sent"));
        } catch (Exception ex) {
            return ResponseEntity.status(GATEWAY_TIMEOUT).
                    body(new ResponseMessage(ex.getCause().getCause().getMessage()));
        }
    }

}
