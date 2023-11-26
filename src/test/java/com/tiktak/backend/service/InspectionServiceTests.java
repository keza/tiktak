package com.tiktak.backend.service;

import com.tiktak.backend.model.dto.AnswerDTO;
import com.tiktak.backend.model.dto.InspectionDto;
import com.tiktak.backend.model.dto.InspectionResponseDTO;
import com.tiktak.backend.model.entity.Inspection;
import com.tiktak.backend.model.entity.Question;
import com.tiktak.backend.repository.AnswerRepository;
import com.tiktak.backend.repository.InspectionRepository;
import com.tiktak.backend.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InspectionServiceTests {

    @Mock
    private InspectionRepository inspectionRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private InspectionService inspectionService;

    @Test
    public void getInspectionDetailsByCarId_whenInspectionDoesNotExist() {
        String carId = "UnknownCarId";
        when(inspectionRepository.findLatestByCarId(carId)).thenReturn(Optional.empty());
        when(questionRepository.findAll()).thenReturn(new ArrayList<>());

        InspectionResponseDTO result = inspectionService.getInspectionDetailsByCarId(carId);

        assertNotNull(result);
        assertEquals(carId, result.getCarId());
    }

    @Test
    public void getInspectionDetailsByCarId_whenInspectionExists() {
        String carId = "123ABC";
        Inspection mockInspection = new Inspection();
        mockInspection.setAnswers(new ArrayList<>());

        when(inspectionRepository.findLatestByCarId(carId)).thenReturn(Optional.of(mockInspection));

        InspectionResponseDTO result = inspectionService.getInspectionDetailsByCarId(carId);

        assertNotNull(result);
        assertEquals(carId, result.getCarId());
    }


    @Test
    public void createInspection_success() {
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

        when(inspectionRepository.save(any(Inspection.class))).thenReturn(new Inspection());
        when(questionRepository.findById(any(Long.class))).thenReturn(Optional.of(new Question()));

        inspectionService.createInspection(inspectionDto);

        verify(answerRepository).saveAll(anyList());
    }

}

