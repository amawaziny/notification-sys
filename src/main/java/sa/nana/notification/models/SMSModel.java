package sa.nana.notification.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import sa.nana.notification.validations.Phone;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class SMSModel {

    @Phone
    private PhoneNumber to;

    @NotBlank
    private String body;
}
