package com.clinicaOdontologica.ClinicaOdontologica.Controller;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.ClinicaOdontologica.exception.ResourceNotFoundExeption;
import com.clinicaOdontologica.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/pacientes")

public class PacienteController {
    //quien representaria al modelo?


    @Autowired
    private PacienteService pacienteService;
    /*public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
*/

    @GetMapping("/{id}")

    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()) {
            //si existe con ese id
            return ResponseEntity.ok(pacienteBuscado.get());

        } else return ResponseEntity.notFound().build();

    }



    @PostMapping // PostM permite crear un nuevo paciente - recibe JSON

    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {

        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));

    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {return ResponseEntity.ok(pacienteService.listarPacientes());}


    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
        //Antes de hacer actualizaciones nos aseguramos que este!

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());

        if (pacienteBuscado.isPresent()) {

            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok().body("Se actualizo el Paciente " + "-" + paciente.getNombre() + paciente.getApellido());

        } else
            return ResponseEntity.badRequest().body("no se puedo actualizar" + paciente.getId() + " -" + paciente.getNombre());

    }

    @DeleteMapping
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundExeption {
       pacienteService.EliminarPaciente(id);
       return  ResponseEntity.ok().body("Se elimino el paciente correctamente. id correspondiente: " + id);

    }
    @GetMapping("{email}")

    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String email) {
        //efectuar la busqueda del paciente

        Optional<Paciente> pacientebuscado = pacienteService.buscarPacientePorCorreo(email);

        if (pacientebuscado.isPresent()) {
            return ResponseEntity.ok(pacientebuscado.get());

        } else return ResponseEntity.notFound().build();


    }

 /*   @GetMapping
   public ResponseEntity<Paciente> buscarPacienteXMail(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorCorreo(paciente.getEmail());
        if (pacienteBuscado.isPresent() && pacienteBuscado.equals(pacienteBuscado.get().getEmail())) {
            return ResponseEntity.ok().body(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */
}






