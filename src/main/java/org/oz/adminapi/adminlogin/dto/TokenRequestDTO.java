package org.oz.adminapi.adminlogin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TokenRequestDTO {

    @NotNull
    private String adminId;

    @NotNull
    private String pw;
}
