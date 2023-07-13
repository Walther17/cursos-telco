import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Cursos } from 'src/app/models/cursos';
import { CursosService } from 'src/app/services/cursos/cursos.service';
import { TokenService } from 'src/app/services/token/token.service';

@Component({
  selector: 'app-listar-cursos',
  templateUrl: './listar-cursos.component.html',
  styleUrls: ['./listar-cursos.component.scss']
})
export class ListarCursosComponent {

  cursos: any;
  isAdmin = false;
  deleteCurso:  Cursos = {
    
    id: 0,
    nombre: '',
    contenido: '',
    estado: '',
   
   
    }

  constructor(
    private cursosService: CursosService,
    private toastr: ToastrService,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.cargarProductos();
    this.isAdmin = this.tokenService.isAdmin();
  }

  cargarProductos(): void {
    this.cursosService.lista().subscribe(
      data => {
        this.cursos = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  
  borrar(id: number) {
    this.cursosService.deleteCursos(id, this.deleteCurso).subscribe(
      data => {
        this.toastr.success('Producto Eliminado', 'OK', {
          timeOut: 2000, positionClass: 'toast-top-center'
        });
        this.cargarProductos();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 2000, positionClass: 'toast-top-center',
        });
      }
    );
  }
  

}