package sa.nana.notification.resources.mobile.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sa.nana.notification.models.MobilePushModel;
import sa.nana.notification.models.Payload;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class MobilePushPayload extends Payload {

    @Valid
    @NotNull(message = "Must provide mobile push message information")
    private MobilePushModel mobilePush;
}
