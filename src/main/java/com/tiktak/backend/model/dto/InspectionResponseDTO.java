package com.tiktak.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InspectionResponseDTO {

    private String carId;
    private List<QuestionWithAnswerDTO> questions;

}
