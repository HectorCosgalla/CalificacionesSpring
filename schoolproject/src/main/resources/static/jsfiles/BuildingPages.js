
function ingresaMultiplesAlumnos() {
    return `<h2>Escribe la lista de alumnos:</h2>
    <form action="/alumnos
    method="post">
    <label for="multiples_estudiantes">Nombre:</label><br>
    <textarea name="students" id="multiples_estudiantes" cols="50" rows="30"></textarea><br>
    <button type="submit">Agregalos</button>
    </form>`
}

function ingresaUnSoloAlumno() {
    return `<h2>Agrega a un nuevo alumno:</h2>
    <form action="/alumno"
    method="post">
    <label for="nombre_nuevo_estudiante">Nombre:</label>
    <input type="text" id="nombre_nuevo_estudiante" name="names" required value><br>
    <label for="primer_apellido_nuevo_estudiante">Apellido paterno:</label>
    <input type="text" id="primer_apellido_nuevo_estudiante" name="middleName" required value><br>
    <label for="segundo_apellido_nuevo_estudiante">Apellido materno:</label>
    <input type="text" id="segundo_apellido_nuevo_estudiante" name="lastName" required value><br>
    <button type="submit">Agregalo</button>
    </form>`
}

function muestraLaOpcion(parametro){
    let htmlAMostrar = null;
    switch (parametro) {
        case 1:
            htmlAMostrar = ingresaUnSoloAlumno();
            break;
        case 2:
            htmlAMostrar = ingresaMultiplesAlumnos();
            break;
        default:
            htmlAMostrar = `<h3>Que hiciste el verano pasado?</h3>`
            break;
    }
    document.body.innerHTML = htmlAMostrar;
}