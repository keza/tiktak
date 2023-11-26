package com.tiktak.backend.service;

import com.tiktak.backend.model.dto.AnswerDTO;
import com.tiktak.backend.model.dto.InspectionDto;
import com.tiktak.backend.model.dto.InspectionResponseDTO;
import com.tiktak.backend.model.dto.QuestionWithAnswerDTO;
import com.tiktak.backend.model.entity.Answer;
import com.tiktak.backend.model.entity.Inspection;
import com.tiktak.backend.repository.AnswerRepository;
import com.tiktak.backend.repository.InspectionRepository;
import com.tiktak.backend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InspectionService {

    private final InspectionRepository inspectionRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional(readOnly = true)
    public InspectionResponseDTO getInspectionDetailsByCarId(String carId) {
        Optional<Inspection> inspectionOpt = inspectionRepository.findLatestByCarId(carId);
        List<QuestionWithAnswerDTO> questionWithAnswerDTOs;

        if (inspectionOpt.isPresent()) {
            Inspection inspection = inspectionOpt.get();
            questionWithAnswerDTOs = inspection.getAnswers().stream()
                    .map(answer -> new QuestionWithAnswerDTO(
                            answer.getQuestion().getId(),
                            answer.getQuestion().getQuestionText(),
                            answer.isAnswer(),
                            answer.getComment(),
                            answer.getPhotoUrls()))
                    .collect(Collectors.toList());
        } else {
            questionWithAnswerDTOs = questionRepository.findAll().stream()
                    .map(question -> new QuestionWithAnswerDTO(
                            question.getId(),
                            question.getQuestionText(),
                            false,
                            null,
                            new ArrayList<>()))
                    .collect(Collectors.toList());
        }

        return new InspectionResponseDTO(carId, questionWithAnswerDTOs);
    }

    @Transactional
    public Inspection createInspection(InspectionDto inspectionDto) {
        Inspection inspection = new Inspection();
        inspection.setCarId(inspectionDto.getCarId());

        Inspection savedInspection = inspectionRepository.save(inspection);

        List<Answer> answers = inspectionDto.getAnswers().stream().map(answerDTO -> {
            validateAnswer(answerDTO);
            Answer answer = new Answer();
            answer.setQuestion(questionRepository.findById(answerDTO.getQuestionId()).orElseThrow());
            answer.setAnswer(answerDTO.isAnswer());
            answer.setComment(answerDTO.getComment());
            answer.setPhotoUrls(answerDTO.getPhotoUrls());
            answer.setInspection(savedInspection);
            return answer;
        }).collect(Collectors.toList());

        answerRepository.saveAll(answers);

        return savedInspection;
    }

    private void validateAnswer(AnswerDTO answerDTO) {
        if (answerDTO.isAnswer()) {
            if (answerDTO.getPhotoUrls() == null || answerDTO.getPhotoUrls().size() < 1 || answerDTO.getPhotoUrls().size() > 3) {
                throw new IllegalArgumentException("En az 1 ve en fazla 3 fotoğraf eklenmelidir.");
            }
            if (answerDTO.getComment() == null || answerDTO.getComment().trim().isEmpty()) {
                throw new IllegalArgumentException("Açıklama zorunludur.");
            }
        }
    }
}
