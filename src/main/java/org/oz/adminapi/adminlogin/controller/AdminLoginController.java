package org.oz.adminapi.adminlogin.controller;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.adminlogin.dto.AdminLoginDTO;
import org.oz.adminapi.adminlogin.dto.TokenRequestDTO;
import org.oz.adminapi.adminlogin.dto.TokenResponseDTO;
import org.oz.adminapi.adminlogin.exception.AdminLoginException;
import org.oz.adminapi.adminlogin.service.AdminLoginService;
import org.oz.adminapi.security.util.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/adminlogin")
@Log4j2
@RequiredArgsConstructor
public class AdminLoginController {

    private final AdminLoginService adminLoginService;

    private final JWTUtil jwtUtil;

    @Value("${org.oz.accessTime}")
    private int accessTime;

    @Value("${org.oz.refreshTime}")
    private int refreshTime;

    @Value("${org.oz.alwaysNew}")
    private boolean alwaysNew;

    @PostMapping("makeToken")
    public ResponseEntity<TokenResponseDTO> makeToken(@RequestBody @Validated TokenRequestDTO tokenRequestDTO) {
        log.info("Making token");
        log.info("------------------------");

        AdminLoginDTO adminLoginDTO = null;
        try {
            adminLoginDTO = adminLoginService.authenticate(
                    tokenRequestDTO.getAdminId(),
                    tokenRequestDTO.getPw()
            );

            if (adminLoginDTO == null) {
                log.error("Authentication failed for adminId: {}", tokenRequestDTO.getAdminId());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } catch (Exception e) {
            log.error("Authentication error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        log.info("Authenticated successfully: {}", adminLoginDTO);

        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("adminId", adminLoginDTO.getAdminID());

        String accessToken = jwtUtil.createToken(claimMap, accessTime);
        String refreshToken = jwtUtil.createToken(claimMap, refreshTime);

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(accessToken);
        tokenResponseDTO.setRefreshToken(refreshToken);
        tokenResponseDTO.setAdminId(adminLoginDTO.getAdminID());

        log.info("Tokens created successfully for adminId: {}", adminLoginDTO.getAdminID());
        log.info("TOKEN: {}", accessToken);

        return ResponseEntity.ok(tokenResponseDTO);
    }

    @PostMapping(value="refreshToken",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestHeader("Authorization") String accessToken, String refreshToken) {

        //만일 accessToken이 없다면 혹은 refreshToken이 없다면
        if(accessToken == null || refreshToken == null) {
            log.info("Please provide accessToken and refreshToken together.");
            throw AdminLoginException.TOKEN_NOT_ENOUGH.get();
        }
        //accessToken Bearer (7) 잘라낼때 문제가 발생한다면
        if(!accessToken.startsWith("Bearer ")) {
            log.info("AccessToken has wrong format.");
            throw AdminLoginException.ACCESSTOKEN_TOO_SHORT.get();
        }
        String accessTokenStr = accessToken.substring("Bearer ".length());

        //AccessToken의 만료 여부 체크
        try{
            Map<String,Object> payload = jwtUtil.validateToken(accessTokenStr);

            String adminId = payload.get("adminId").toString();

            TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
            tokenResponseDTO.setAccessToken(accessTokenStr);
            tokenResponseDTO.setAdminId(adminId);
            tokenResponseDTO.setRefreshToken(refreshToken);

            return ResponseEntity.ok(tokenResponseDTO);

        }catch(ExpiredJwtException ex){
            //정상적으로 만료된 경우

            //만일 Refresh Token마저 만료되었다면 catch
            try{
                Map<String,Object> payload = jwtUtil.validateToken(refreshToken);
                String adminId = payload.get("adminId").toString();
                String newAccesToken = null;
                String newRrefreshToken = null;

                if(alwaysNew) {
                    Map<String, Object> claimMap = Map.of("adminId", adminId);
                    newAccesToken = jwtUtil.createToken(claimMap,accessTime);
                    newRrefreshToken = jwtUtil.createToken(claimMap,refreshTime);
                }
                TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
                tokenResponseDTO.setAccessToken(newAccesToken);
                tokenResponseDTO.setRefreshToken(newRrefreshToken);
                tokenResponseDTO.setAdminId(adminId);

                return ResponseEntity.ok(tokenResponseDTO);

            }catch(ExpiredJwtException ex2){
                throw AdminLoginException.REQUIRE_SIGN_IN.get();
            }
        }
    }

}
