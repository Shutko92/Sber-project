package org.application.sbertask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewClientRequest {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
}
