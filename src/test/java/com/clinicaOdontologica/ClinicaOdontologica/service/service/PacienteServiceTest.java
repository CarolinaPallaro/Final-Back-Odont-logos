package com.clinicaOdontologica.ClinicaOdontologica.service.service;

import com.clinicaOdontologica.ClinicaOdontologica.entity.Domicilio;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.ClinicaOdontologica.exception.ResourceNotFoundExeption;
import com.clinicaOdontologica.ClinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Test
    @Order(1)
    public void guardarPaciente(){
        Paciente pacienteAGuardar= new Paciente("Jorgito","Pereyra","12345", LocalDate.of(2023,03,29),new Domicilio("jaramillo",90,"La Rioja","La Rioja"),"jorge@gmail.com");
        Paciente pacienteGuardado= pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L,pacienteAGuardar.getId());
    }
    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idAbuscar=1L;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(idAbuscar);
        assertNotNull(pacienteBuscado.get());
    }
    @Test
    @Order(3)
public void actualizarPacienteTest(){
        Paciente pacienteActualizar= new Paciente(1L,"Agustin","Pereyra","12345", LocalDate.of(2023,03,29),new Domicilio("jaramillo",90,"La Rioja","La Rioja"),"jorge@gmail.com");
        pacienteService.actualizarPaciente(pacienteActualizar);
        Optional<Paciente> pacienteActalizado= pacienteService.buscarPaciente(1L);
        assertEquals("Agustin",pacienteActalizado.get().getNombre());

    }
    @Test
    @Order(4)
    public void buscarTodos(){
        List<Paciente> pacientes= pacienteService.listarPacientes();
        assertEquals(1,pacientes.size());
    }
 @Test
 @Order(5)
 public void eliminarPacienteTest() throws ResourceNotFoundExeption {
        Long idAbuscar= 1L;
        pacienteService.EliminarPaciente(idAbuscar);
        Optional<Paciente> pacienteEliminado= pacienteService.buscarPaciente(idAbuscar);
        assertFalse(pacienteEliminado.isPresent());

 }


}