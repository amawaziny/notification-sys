package sa.nana.notification.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class Payload implements Serializable {
    private long delay;
}
