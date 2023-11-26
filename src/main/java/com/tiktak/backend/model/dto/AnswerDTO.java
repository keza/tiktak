package com.tiktak.backend.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerDTO {

    private Long questionId;
    private boolean answer;
    private String comment;
    private List<String> photoUrls;
}
