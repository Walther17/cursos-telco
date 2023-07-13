import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario.model';
import {environment} from '../../../environments/environments'
import {Subject} from 'rxjs'
import { tap } from 'rxjs/operators'
import { Rol } from '../models/rol.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private _refresh$ = new Subject<void>();


  constructor(
    private httpClient: HttpClient,
  ) { }


  get refresh$(){
    return this._refresh$;
  }

  getAllUsuarios(): Observable<Usuario[]> {
    return this.httpClient.get<Usuario[]>(environment.url_api);
  }

   
  getAllRoles(): Observable<Rol[]> {
    return this.httpClient.get<Rol[]>(environment.url_roles);
  }

  createUsuario(usuario:Usuario): Observable<Object>{
    return this.httpClient.post(`${environment.url_api}`, usuario);
  }

  deleteUsuario(id:number,  usuario:Usuario  ): Observable<Object>{
    return  this.httpClient.put(`${environment.url_api_delete}/${id}`, usuario)
    .pipe(
      tap( () => {
        this._refresh$.next;
      })
    )
  }

  getUsuarioById(id:number): Observable<Object>{
    return  this.httpClient.get(`${environment.url_api}/${id}`);
  }

  updateUsuario(id:number,  usuario:Usuario): Observable<Object>{
    return  this.httpClient.put(`${environment.url_api}/${id}`, usuario);
  }

 

  
}
