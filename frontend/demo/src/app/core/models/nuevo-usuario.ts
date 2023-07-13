export class NuevoUsuario {
    nombre: string;
    apellido: string;
    estado: string;
    nombreUsuario: string;
    email: string;
    password: string;


    constructor(nombre: string, apellido: string, estado: string, nombreUsuario: string, email: string, password: string) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }
}
