package sa.nana.notification.resources.sms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sa.nana.notification.models.Payload;
import sa.nana.notification.models.SMSModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class SMSPayload extends Payload {

    @Valid
    @NotNull(message = "Must provide SMS message information")
    private SMSModel sms;
}
