package sa.nana.notification.resources.email.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sa.nana.notification.models.EmailModel;
import sa.nana.notification.models.Payload;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class EmailPayload extends Payload {

    @Valid
    @NotNull(message = "Must provide Email information")
    private EmailModel email;
}
