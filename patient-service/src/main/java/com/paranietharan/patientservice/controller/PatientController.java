package com.paranietharan.patientservice.controller;

import com.paranietharan.patientservice.dto.PatientRequestDTO;
import com.paranietharan.patientservice.dto.PatientResponseDTO;
import com.paranietharan.patientservice.model.Patient;
import com.paranietharan.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patientResponseDTOList = patientService.getPatients();
        return new ResponseEntity<>(patientResponseDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> savePatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return new ResponseEntity<>(patientResponseDTO, HttpStatus.OK);
    }
}
