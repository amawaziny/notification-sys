package sa.nana.notification.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {

    @NotBlank
    private String value;

    @NotBlank
    private String locale;
}