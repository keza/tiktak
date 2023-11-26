package com.tiktak.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionWithAnswerDTO {

    private Long questionId;
    private String questionText;
    private boolean answer;
    private String comment;
    private List<String> photoUrls;

}
