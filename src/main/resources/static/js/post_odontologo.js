window.onload = () => {
    const formulario = document.getElementById("formularioOdontologo");

    formulario.addEventListener("submit", event => {
        event.preventDefault();
        agregarOdontologo();
    });

}

function agregarOdontologo() {
    const nombre = document.getElementById("nombre");
    const apellido = document.getElementById("apellido");
    const matricula = document.getElementById("matricula");

    const formData = {
        nombre: nombre.value,
        apellido: apellido.value,
        matricula: matricula.value,
    };

    const settings = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
    };

    fetch("http://localhost:8082/odontologos/", settings)
      .then((response) => response.json())
      .then(() => {
          manejadorMensajeExito()


      })
      .catch(() => alert("No se pudo crear el odontólogo"))
      .finally(() => {
          nombre.value = "";
          apellido.value = "";
          matricula.value = "";

      })

    function manejadorMensajeExito(){
        let successAlert = '<div class="alert alert-success ">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            '<strong></strong> Odontologo Creado </div>'

        document.querySelector('#response').innerHTML = successAlert;
        document.querySelector('#response').style.display = "block";
        let btnCerrar= document.querySelector('.close');
        btnCerrar.addEventListener('click', () => {
                document.querySelector('#response').style.display = "none";
            }
        );

        setTimeout(() => {
            document.querySelector('#response').style.display = "none";
        }, 3000);

    }
}