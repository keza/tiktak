package com.tiktak.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiktak.backend.model.dto.AnswerDTO;
import com.tiktak.backend.model.dto.InspectionDto;
import com.tiktak.backend.model.dto.InspectionResponseDTO;
import com.tiktak.backend.service.InspectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InspectionController.class)
public class InspectionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InspectionService inspectionService;

    @Test
    public void getInspectionByCarId_whenInspectionExists() throws Exception {
        String carId = "123ABC";
        InspectionResponseDTO mockResponse = new InspectionResponseDTO(carId, new ArrayList<>());

        given(inspectionService.getInspectionDetailsByCarId(carId)).willReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inspections/" + carId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carId").value(carId));
    }

    @Test
    public void getInspectionByCarId_whenInspectionDoesNotExist() throws Exception {
        String carId = "UnknownCarId";
        given(inspectionService.getInspectionDetailsByCarId(carId)).willReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inspections/" + carId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createInspection_whenPostMethod() throws Exception {
        InspectionDto inspectionDto = new InspectionDto();
        inspectionDto.setCarId("123ABC");
        List<AnswerDTO> answers = new ArrayList<>();
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setQuestionId(1L);
        answerDTO.setAnswer(true);
        answerDTO.setComment("Test comment");
        answerDTO.setPhotoUrls(Arrays.asList("url1.jpg", "url2.jpg"));
        answers.add(answerDTO);
        inspectionDto.setAnswers(answers);

        ObjectMapper objectMapper = new ObjectMapper();
        String inspectionDtoJson = objectMapper.writeValueAsString(inspectionDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/inspections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inspectionDtoJson))
                .andExpect(status().isOk());
    }
}


