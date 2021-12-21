package com.swoqe.admissionscommittee.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PersonDto extends UuidBasedDto {
    private String firstName;
    private String lastName;
    private String email;
}
