import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../../models/usuario.model';
import { Observable } from 'rxjs';



const s  = sessionStorage.getItem('AuthToken');
const headers = new HttpHeaders().set('Authorization', `Bearer ${s}`);


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  usuarios = 'http://localhost:8080/auth/usuarios';


  constructor(private httpClient: HttpClient) { }

  getAllUsuarios(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(this.usuarios , {headers});
  }
}
