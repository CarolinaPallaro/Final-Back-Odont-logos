package com.clinicaOdontologica.ClinicaOdontologica.dto;

import java.time.LocalDate;

public class TurnoDTO {
    private Long Id;
    private LocalDate fecha;
    private Long PacienteId;
    private Long OdontologoId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getPacienteId() {
        return PacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        PacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return OdontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        OdontologoId = odontologoId;
    }

    @Override
    public String toString() {
        return "TurnoDTO{" +
                "Id=" + Id +
                ", fecha=" + fecha +
                ", PacienteId=" + PacienteId +
                ", OdontologoId=" + OdontologoId +
                '}';
    }
}
