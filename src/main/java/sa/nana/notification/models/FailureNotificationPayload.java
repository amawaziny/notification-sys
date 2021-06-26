package sa.nana.notification.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailureNotificationPayload {
    private String exceptionMessage;
    private Payload payload;
}
