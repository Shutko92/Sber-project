package org.application.sbertask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.application.sbertask.model.ClientStatus;

@Getter
@Setter
@Builder
public class UpdateClientRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private ClientStatus clientStatus;
}
