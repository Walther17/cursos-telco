import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/assets/environments/environment';
import { Cursos } from 'src/app/models/cursos';
import { Observable } from 'rxjs';

const s  = localStorage.getItem('AuthToken');
const headers = new HttpHeaders().set('Authorization', `Bearer ${s}`);


@Injectable({
  providedIn: 'root'
})
export class CursosService {

  constructor(private httpClient: HttpClient) { }

  cursosURL = environment.cursosURL;


  public lista(): Observable<Cursos[]> {
    return this.httpClient.get<Cursos[]>(this.cursosURL + 'listar-cursos');
  }

  createCursos(Cursos:Cursos): Observable<Object>{
    return this.httpClient.post(this.cursosURL + 'create', Cursos );
  }

  deleteCursos(id:number,  Cursos:Cursos  ): Observable<Object>{
    return  this.httpClient.put(this.cursosURL +  `delete/${id}`, Cursos)
    
  }

  getCursosById(id:number): Observable<Cursos>{
    return  this.httpClient.get<Cursos>( this.cursosURL  +  `detail/${id}`);
  }

  updateCursos(id:number,  Cursos:Cursos): Observable<Object>{
    return  this.httpClient.put(this.cursosURL +  `update/${id}`, Cursos);
  }

}