package com.clinicaOdontologica.ClinicaOdontologica.service;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Paciente;
import com.clinicaOdontologica.ClinicaOdontologica.exception.ResourceNotFoundExeption;
import com.clinicaOdontologica.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


//creamos el iDAO
@Service
public class PacienteService {
    //definimos el atributo
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {this.pacienteRepository = pacienteRepository;}


    //CREAR-GUARDAR UN PACIENTE
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    //BUSCAR PACIENTE X ID
    public Optional<Paciente> buscarPaciente(Long id) {
        return pacienteRepository.findById(id);
    }

    //ACTUALIZAR PACIENTE
    public void actualizarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    //ELIMINAR PACIENTE X ID
    public void EliminarPaciente(Long id) throws ResourceNotFoundExeption {
    Optional<Paciente> pacienteADeletear = pacienteRepository.findById(id);
    if (pacienteADeletear.isPresent()){
        pacienteRepository.deleteById(id);
    } else  throw new ResourceNotFoundExeption("este id: " + id + "No se puede eliminar, no se encuentra en base");

    }
    //LISTAR PACIENTES
    public List<Paciente> listarPacientes(){
       return pacienteRepository.findAll();
    }

    //BUSCAR PACIENTE POR CORREO
    public Optional<Paciente> buscarPacientePorCorreo(String correo) {
        return pacienteRepository.findByEmail(correo);
    }
}
