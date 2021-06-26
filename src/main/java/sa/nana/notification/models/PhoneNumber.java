package sa.nana.notification.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber implements Serializable {

    @NotBlank
    private String value;

    @NotBlank
    private String locale;
}