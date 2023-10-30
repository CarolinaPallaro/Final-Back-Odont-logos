window.addEventListener('load', function () {
    const url= '/odontologos/';
    const settings = {
        method: 'GET'
    }



    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            for(odontologo of data) {
                console.log(odontologo)
                let table = document.getElementById("odontologoTable");
                let odontologoRow = table.insertRow();
                let tr_id = 'tr_' + odontologo.id;
                odontologoRow.id = tr_id;

                let botonActualizar = '<button ' +
                                                ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                                ' type="button" onclick="findBy('+odontologo.id+')" class="mr-2 btn btn-outline-info btn_id">' +
                                                    "✍🏻" +
                                                '</button>';

                let botonBorrar = '<button' +
                    ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                    ' type="button" onclick="deleteBy('+odontologo.id+')" class=" ml-1 btn btn-outline-danger btn_delete">' +
                    '&times' +
                    '</button>';

                odontologoRow.innerHTML =
                    // '<td >' + botonActualizar + '</td>' +
                    // '<td class=\"td_id\">' + odontologo.id.toUpperCase() + '</td>' +
                    '<td class=\"td_id\">' + odontologo.id + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>'+
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>'+
                    '<td >'+ botonActualizar + botonBorrar + '</td>' ;

            }

        });
})