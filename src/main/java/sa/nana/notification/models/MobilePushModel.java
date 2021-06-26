package sa.nana.notification.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class MobilePushModel {

    private String deviceId;

    @NotNull
    private OsType os;

    @NotBlank
    private String body;
}
