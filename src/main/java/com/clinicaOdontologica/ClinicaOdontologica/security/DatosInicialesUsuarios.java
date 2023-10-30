package com.clinicaOdontologica.ClinicaOdontologica.security;

import com.clinicaOdontologica.ClinicaOdontologica.entity.Usuario;
import com.clinicaOdontologica.ClinicaOdontologica.entity.UsuarioRol;
import com.clinicaOdontologica.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesUsuarios implements ApplicationRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passSinCifrar= "digital";
        String passCifrado = cifrador.encode(passSinCifrar);
        Usuario usuarioAInsertar = new Usuario("Caro", "carop", "caro@gmail.com",passCifrado, UsuarioRol.ROLE_USER);

        usuarioRepository.save(usuarioAInsertar);

        //CREAMOS UN ROL ADMIN

        String passinCifrar2 = "house";
        String passCifrado2 = cifrador.encode(passinCifrar2);
        Usuario usuarioAInsertar2 = new Usuario("juan", "juancho","juan@gmail.com", passCifrado2, UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(usuarioAInsertar2);
    }
}
