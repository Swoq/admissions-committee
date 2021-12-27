package com.swoqe.admissionscommittee.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class FacultyDto {
    private UUID id;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 1, max=255, message = "Title must be less than 255 symbols")
    private String name;

    @NotNull
    @Positive
    private int sponsoredSlots;

    @NotNull
    @Positive
    private int availableSlots;
}
