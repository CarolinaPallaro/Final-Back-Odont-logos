package com.clinicaOdontologica.ClinicaOdontologica.service;
import com.clinicaOdontologica.ClinicaOdontologica.entity.Odontologo;
import com.clinicaOdontologica.ClinicaOdontologica.exception.ResourceNotFoundExeption;
import com.clinicaOdontologica.ClinicaOdontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private OdontologoRepository odontologoRepository;
    //necesito ahora darle memoria

@Autowired
        //Constructor
    public OdontologoService(OdontologoRepository odontologoRepository) {this.odontologoRepository = odontologoRepository;}

    //GUARDAR ODONTOLOGO
    public Odontologo guardarOdontologo(Odontologo odontologo){return odontologoRepository.save(odontologo);}

    //BUSCAR ODONTOLOGO POR ID
    public Optional<Odontologo> buscarOdontologo(Long id){return odontologoRepository.findById(id);}

    //ACTUALIZAR ODONTOLOGO
    public void actualizarOdontologo(Odontologo odontologo){odontologoRepository.save(odontologo);}

    //ELIMINAR ODONTOLOGO X ID
    public void eliminarOdontologo(Long id) throws ResourceNotFoundExeption {
    Optional <Odontologo> odontologoADeletear = buscarOdontologo(id);
    if(odontologoADeletear.isPresent()){
        odontologoRepository.deleteById(id);
    }
        else{
            throw new ResourceNotFoundExeption("este id: " + id + "No se puede eliminar, no se encuentra en base");
    }
    }

    //BUSCAR TODOS
    public List<Odontologo> listarOdontologos(){return odontologoRepository.findAll();}




}
