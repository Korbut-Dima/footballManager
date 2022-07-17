package com.footballManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {
    @NotNull
    private Long player;
    @NotNull
    private Long team;

}
