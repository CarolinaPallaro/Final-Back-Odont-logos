
package com.clinicaOdontologica.ClinicaOdontologica.Controller;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Odontologo;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.ClinicaOdontologica.exception.ResourceNotFoundExeption;
import com.clinicaOdontologica.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;


  /*  public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
*/


    @PostMapping // REGISTRO DE ODONTO

    public ResponseEntity <Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {

        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }


    @GetMapping("/{id}") //BUSCO ODONTO POR ID

    public ResponseEntity <Odontologo> buscarOdontologos(@PathVariable Long id) {

       Optional< Odontologo> buscarOdontologo = odontologoService.buscarOdontologo(id);
        if (buscarOdontologo.isPresent()) {
            return ResponseEntity.ok(buscarOdontologo.get());

        } else return ResponseEntity.notFound().build();
    }

    @PutMapping //ACTUALIZO ODONTO

    public ResponseEntity <String> actualizarOdontologo(@RequestBody Odontologo odontologo) {

        Optional<Odontologo> buscarOdontologo = odontologoService.buscarOdontologo(odontologo.getId());

        if (buscarOdontologo.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);

            return ResponseEntity.ok().body( odontologo.getNombre() + "Se actualizo");

        } else return ResponseEntity.badRequest().body(odontologo.getNombre() + "no se encontro");

    }

    @DeleteMapping("/{id}") //ELIMINAR ODONTO X ID

    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws Exception{

       odontologoService.eliminarOdontologo(id);
       return ResponseEntity.ok("Se elimino al odontologo correctamemte. id correspondiente: " + id);

    }

        @GetMapping // LISTAR ODONTOS
        public ResponseEntity<List<Odontologo>> listarTodosOdontologos() {
            return ResponseEntity.ok(odontologoService.listarOdontologos());
        }



    }

