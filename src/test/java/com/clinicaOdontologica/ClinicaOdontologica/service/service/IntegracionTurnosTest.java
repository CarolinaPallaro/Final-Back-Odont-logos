package com.clinicaOdontologica.ClinicaOdontologica.service.service;
import com.clinicaOdontologica.ClinicaOdontologica.dto.TurnoDTO;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Domicilio;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Odontologo;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.ClinicaOdontologica.service.OdontologoService;
import com.clinicaOdontologica.ClinicaOdontologica.service.PacienteService;
import com.clinicaOdontologica.ClinicaOdontologica.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

public class IntegracionTurnosTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;


    //metodo para cargar datos

    private void cargarDatos(){
        Paciente pacienteAgregado = pacienteService.guardarPaciente(new Paciente("Pablo", "Merino", "627442", LocalDate.of(2023,4, 05), new Domicilio("Vagancia",30,"La pampa", "San Juan"), "PabloMerino@Gmail.com"));

        Odontologo odontologoAgregado = odontologoService.guardarOdontologo(new Odontologo("10033","Romina","Martin"));

        TurnoDTO turnoDTO= new TurnoDTO();

        turnoDTO.setFecha(LocalDate.of(2023,05,20));
        turnoDTO.setOdontologoId(odontologoAgregado.getId());
        turnoDTO.setPacienteId(pacienteAgregado.getId());
        turnoService.guardarTurno(turnoDTO);
    }

    //LO QUE ESTA ENCIMA LO MANDA AL METODO DE ABAJO EN FORMATO JSON

    @Test
    public  void ListarTurnosTest() throws Exception{
        //funcionalidades
        //guardar y ejecutar guardando datos

        cargarDatos();
        MvcResult rta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(rta.getResponse().getContentAsString().isEmpty());

    }


}
