import { Rol } from "./rol.model";

export interface Usuario{
    id: number;
    nombre: string;
    apellido: string;
    nombreUsuario: string;
    email: string;
    estado: string;
    password: string;
    rol: Rol
}