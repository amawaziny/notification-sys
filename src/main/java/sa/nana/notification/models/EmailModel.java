package sa.nana.notification.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class EmailModel implements Serializable {

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    @NotBlank
    @Email
    private String to;

    @Email
    private String cc;

    @Email
    @NotBlank
    private String from;
}
