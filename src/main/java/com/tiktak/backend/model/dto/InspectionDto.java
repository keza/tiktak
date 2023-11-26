package com.tiktak.backend.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class InspectionDto {

    private String carId;
    private List<AnswerDTO> answers;
}
