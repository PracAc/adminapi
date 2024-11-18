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

    private String makerName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
