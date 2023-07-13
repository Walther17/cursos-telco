export class Cursos {
    id : number;
    nombre: string;
    contenido: string;

     estado: string;
  
    constructor(nombre: string,  contendio: string,  estado:  string,) {
        this.nombre = nombre;
        this.contenido = contendio;
        this.estado = estado;
       
    }
}