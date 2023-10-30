package com.clinicaOdontologica.ClinicaOdontologica.Controller;

import com.clinicaOdontologica.ClinicaOdontologica.dto.TurnoDTO;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Turno;
import com.clinicaOdontologica.ClinicaOdontologica.exception.ResourceNotFoundExeption;
import com.clinicaOdontologica.ClinicaOdontologica.service.OdontologoService;
import com.clinicaOdontologica.ClinicaOdontologica.service.PacienteService;
import com.clinicaOdontologica.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired

    private TurnoService turnoService;
    @Autowired

    private  PacienteService pacienteService;
    @Autowired

    private OdontologoService odontologoService;

 /*   public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }
*/


    @PostMapping
    public  ResponseEntity<Turno> registrarTurno(@RequestBody TurnoDTO turnoDTO){

        /*OdontologoService odontologoService = new OdontologoService();
        PacienteService pacienteService = new PacienteService();
        */

        if (odontologoService.buscarOdontologo(turnoDTO.getOdontologoId()).isPresent() &&
                pacienteService.buscarPaciente(turnoDTO.getPacienteId()).isPresent()){


                 return ResponseEntity.ok(turnoService.guardarTurno(turnoDTO));

        }else {
                return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodosTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }


    @GetMapping("/{id}")
    public  ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){

       Optional <TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            return  ResponseEntity.ok().body(turnoBuscado.get());

        }else return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}") //ELIMINAR TURNO

    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws Exception {
       Optional <TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);

        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se elimino el turno con id: " + id);

        }else{
            throw new ResourceNotFoundExeption("No se encontro el paciente que quiere eliminar" + id);
         // return ResponseEntity.badRequest().body("No se encontro el turno con id: " + id);
        }

    }

    @PutMapping()

    public  ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnoDTO) {
        //Buscamos con el id del turno, paciente u odontologo



        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(turnoDTO.getId());
        if (turnoBuscado.isPresent()) {
                /*OdontologoService odontologoService = new OdontologoService();
                PacienteService pacienteService = new PacienteService(); */

            if (odontologoService.buscarOdontologo(turnoDTO.getOdontologoId()).isPresent() &&
                    pacienteService.buscarPaciente(turnoDTO.getPacienteId()).isPresent()) {
                turnoService.actualizarTurno(turnoDTO);

                return ResponseEntity.ok(turnoBuscado.toString());

            } else

                return ResponseEntity.badRequest().body("No se puede actualizar el turno" + turnoDTO.toString() );

        } else return ResponseEntity.badRequest().body("El turno" + turnoDTO.toString());
    }



}
