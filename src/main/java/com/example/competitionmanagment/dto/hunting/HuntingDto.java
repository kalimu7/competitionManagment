package com.example.competitionmanagment.dto.hunting;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class HuntingDto {

    public int id;

    @Positive(message = "number of fish must be positive")
    public int numberOfFish;
    @NotBlank(message = "fishname is required")
    public String fishname;
    @NotBlank(message = "competition code is required")
    public String competitioncode;

    @NotNull(message = "mumbernum is required")
    public int membernum;

    @Positive(message = "weight must be  positive")
    public float weight;


}
