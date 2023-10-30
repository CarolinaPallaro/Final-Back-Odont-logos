package com.clinicaOdontologica.ClinicaOdontologica.service;

import com.clinicaOdontologica.ClinicaOdontologica.dto.TurnoDTO;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Odontologo;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Turno;
import com.clinicaOdontologica.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private TurnoRepository turnoRepository;

    //constructor
    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {this.turnoRepository = turnoRepository;}


private  TurnoDTO turnoAturnoDTO(Turno turno){
    TurnoDTO respuesta = new TurnoDTO();
    //cargar la info de un turno a un turnoiDTO
    respuesta.setId(turno.getId());
    respuesta.setPacienteId(turno.getPaciente().getId());
    respuesta.setOdontologoId(turno.getOdontologo().getId());
    respuesta.setFecha(turno.getFecha());

    //devuelvo la nueva info cargada
    return  respuesta;
}
    public Turno guardarTurno(TurnoDTO turnoDTO){
         Turno turnoGuardado = turnoRepository.save(turnoDTOaTurno(turnoDTO));
        return turnoGuardado;
    }

    public Optional<TurnoDTO> buscarTurno(Long id){
    Optional<Turno> turnoBuscado = turnoRepository.findById(id);
    if(turnoBuscado.isPresent()){
        return  Optional.of(turnoAturnoDTO(turnoBuscado.get()));
    }else return Optional.empty();}



    public List<TurnoDTO> listarTurnos(){

        List<Turno> turnosEncontrados = turnoRepository.findAll();
        //Recorro la lista para convertir cada elemento
        List<TurnoDTO> respuesta = new ArrayList<>();
        for (Turno turno: turnosEncontrados){
            respuesta.add(turnoAturnoDTO(turno));
        }
        return  respuesta;
    }
    public void eliminarTurno(Long id){turnoRepository.deleteById(id);}


    public void actualizarTurno(TurnoDTO turnoDTO){turnoRepository.save(turnoDTOaTurno(turnoDTO));}


    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        //Convertir un DTO a uno comun
        Turno respuesta = new Turno();
        Odontologo odontologo =  new Odontologo();
        Paciente paciente = new Paciente();

        //carga de info
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        respuesta.setFecha(turnoDTO.getFecha());
        respuesta.setId(turnoDTO.getId());

        respuesta.setOdontologo(odontologo);
        respuesta.setPaciente(paciente);
        //salida

        return respuesta;
    }




}

