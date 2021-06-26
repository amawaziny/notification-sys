package sa.nana.notification.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailureNotificationPayload implements Serializable {
    private String exceptionMessage;
    private Payload payload;
}
