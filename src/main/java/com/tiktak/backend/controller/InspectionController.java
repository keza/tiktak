package com.tiktak.backend.controller;

import com.tiktak.backend.model.dto.InspectionDto;
import com.tiktak.backend.model.dto.InspectionResponseDTO;
import com.tiktak.backend.service.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inspections")
@RequiredArgsConstructor
public class InspectionController {

    private final InspectionService inspectionService;

    @GetMapping("/{carId}")
    public ResponseEntity<InspectionResponseDTO> getInspectionByCarId(@PathVariable String carId) {
        InspectionResponseDTO inspectionResponse = inspectionService.getInspectionDetailsByCarId(carId);
        if (inspectionResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inspectionResponse);
    }

    @PostMapping
    public ResponseEntity<?> createInspection(@RequestBody InspectionDto inspectionDto) {
        inspectionService.createInspection(inspectionDto);
        return ResponseEntity.ok().build();
    }
}
