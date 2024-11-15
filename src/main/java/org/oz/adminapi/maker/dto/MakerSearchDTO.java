package org.oz.adminapi.maker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakerSearchDTO {

    private String makerName = "choi";

    private LocalDateTime startDate = LocalDateTime.parse("2024-11-09T07:26:42", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

    private LocalDateTime endDate = LocalDateTime.parse("2024-11-13T07:26:46", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
}
