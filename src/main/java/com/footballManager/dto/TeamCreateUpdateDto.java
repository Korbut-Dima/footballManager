package com.footballManager.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamCreateUpdateDto {

    private String name;
    private Float commissionForTransfer;
    private BigDecimal balance;

}
