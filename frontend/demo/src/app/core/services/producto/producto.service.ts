import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../../models/producto';
import { environment } from 'src/environments/environments';
 
const s  = localStorage.getItem('AuthToken');
const headers = new HttpHeaders().set('Authorization', `Bearer ${s}`);

@Injectable({
  providedIn: 'root'
})

export class ProductoService {

  productoURL = environment.productoURL
  

  constructor(private httpClient: HttpClient) { }

   lista(): Observable<Producto[]> {
    return this.httpClient.get<Producto[]>(this.productoURL + 'lista',  {headers});
  }

  public detail(id: number): Observable<Producto> {
    return this.httpClient.get<Producto>(this.productoURL + `detail/${id}`,  {headers});
  }

  public detailName(nombre: string): Observable<Producto> {
    return this.httpClient.get<Producto>(this.productoURL + `detailname/${nombre}`,  {headers});
  }

  public save(producto: Producto): Observable<any> {
    return this.httpClient.post<any>(this.productoURL + 'create', producto,  {headers});
  }

  public update(id: number, producto: Producto): Observable<any> {
    return this.httpClient.put<any>(this.productoURL + `update/${id}`, producto,  {headers});
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.productoURL + `delete/${id}`,  {headers});
  }
}
