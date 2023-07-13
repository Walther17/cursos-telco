import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Usuario } from 'src/app/models/usuario';
import { TokenService } from 'src/app/services/token/token.service';
import { UsuariosService } from 'src/app/services/usuarios/usuario.service';

@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.scss']
})
export class ListaUsuariosComponent {


    usuarios: any;
   isAdmin = false;
   deleteUsuario:  Usuario = {
     
     id: 0,
     nombre: '',
     apellido: '',
     nombreUsuario: '',
     estado: '',
     email: '',
     password: '',
    
     }
 
   constructor(
     private usuariosService: UsuariosService,
     private toastr: ToastrService,
     private tokenService: TokenService
   ) { }
 
   ngOnInit() {
     this.cargarProductos();
     this.isAdmin = this.tokenService.isAdmin();
   }
 
   cargarProductos(): void {
     this.usuariosService.lista().subscribe(
       data => {
         this.usuarios = data;
       },
       err => {
         console.log(err);
       }
     );
   }
 
   
   borrar(id: number) {
     this.usuariosService.deleteUsuario(id, this.deleteUsuario).subscribe(
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
 