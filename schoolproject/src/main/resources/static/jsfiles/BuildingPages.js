
function ingresaMultiplesAlumnos(params) {

    document.body.innerHTML = '<h2>Escribe la lista de alumnos:</h2>' +
    '<form action="/alumnos"'+
    'method="post">'+
    '<label for="multiples_estudiantes">Nombre:</label><br>'+
    '<textarea name="alumnos" id="multiples_estudiantes" cols="50" rows="30"></textarea><br>'+
    '<button type="submit">Agregalos</button>'+
    '</form>'
    
}

function ingresaUnSoloAlumno(params) {
    document.body.innerHTML = '<h2>Agrega a un nuevo alumno:</h2>'+
    '<form action="/alumno"'+
    'method="post">'+
    '<label for="nombre_nuevo_estudiante">Nombre:</label>'+
    '<input type="text" id="nombre_nuevo_estudiante" name="nombres" required value><br>'+
    '<label for="primer_apellido_nuevo_estudiante">Apellido paterno:</label>'+
    '<input type="text" id="primer_apellido_nuevo_estudiante" name="primerApellido" required value><br>'+
    '<label for="segundo_apellido_nuevo_estudiante">Apellido materno:</label>'+
    '<input type="text" id="segundo_apellido_nuevo_estudiante" name="segundoApellido" required value><br>'+
    '<button type="submit">Agregalo</button>'+
    '</form>'
}

function displayATitle(){
    document.body.innerHTML = "<h3> a wild title has appeared! </h3>"
}