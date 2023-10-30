package com.clinicaOdontologica.ClinicaOdontologica.repository;

import com.clinicaOdontologica.ClinicaOdontologica.entity.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByEmail(String correo);

}
