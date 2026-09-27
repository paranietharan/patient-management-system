package com.paranietharan.patientservice.service;

import com.paranietharan.patientservice.dto.PatientRequestDTO;
import com.paranietharan.patientservice.dto.PatientResponseDTO;
import com.paranietharan.patientservice.mapper.PatientMapper;
import com.paranietharan.patientservice.model.Patient;
import com.paranietharan.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients
                .stream()
                .map(
                        PatientMapper::toDTO
                ).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO)
        );
        return PatientMapper.toDTO(patient);
    }
}
