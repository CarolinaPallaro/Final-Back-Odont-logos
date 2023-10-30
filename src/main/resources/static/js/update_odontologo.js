window.addEventListener('load', function () {
    let formularioActualizar = document.querySelector('#div_odontologo_updating')


    formularioActualizar.addEventListener('submit', function (event) {
        event.preventDefault();

        const url = '/odontologos';
        const formData = {
            id: document.querySelector('#id').value,
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
        };

        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url,settings)
            .then(response => response.json())

    })




})



function findBy(id) {
    const url = "/odontologos/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            console.log(odontologo)
            document.querySelector('#id').value = odontologo.id;
            document.querySelector('#matricula').value = odontologo.matricula;
            document.querySelector('#nombre').value = odontologo.nombre;
            document.querySelector('#apellido').value = odontologo.apellido;
            //el formulario por default esta oculto y al editar se habilita
            //el formulario por default esta oculto y al editar se habilita
            let formularioActualizar = document.querySelector('#div_odontologo_updating')
            toggleDisplay(formularioActualizar)
        }).catch(error => {
        console.log(error);
    })

    const toggleDisplay = target => target.style.display =
        (target.style.display == 'none') ?
            'block' :
            'none'
}
