package org.oz.adminapi.adminlogin.dto;

import lombok.Data;

@Data
public class TokenResponseDTO {

    private String adminId;
    private String accessToken;
    private String refreshToken;
    private int status;
    private String message;
}
