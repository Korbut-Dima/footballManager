package com.footballManager.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamCreateUpdateDto {
    @NotNull
    @Size(min = 2,max = 255, message = "Tame of team should be between 3 and 255 characters")
    @Pattern(regexp = "[a-zA-Z ]*", message = "Tame of team cannot contain any special characters and digits")
    private String name;
    @NotNull
    @Positive
    private Float commissionForTransfer;
    @NotNull
    @PositiveOrZero
    private BigDecimal balance;

}
