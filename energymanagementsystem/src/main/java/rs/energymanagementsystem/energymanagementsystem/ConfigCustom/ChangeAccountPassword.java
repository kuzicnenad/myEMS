package rs.energymanagementsystem.energymanagementsystem.ConfigCustom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeAccountPassword {

    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
