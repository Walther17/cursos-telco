import { Component, OnInit,  ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from 'src/app/core/models/usuario.model';
import { UsuarioService } from 'src/app/core/services/usuario/usuario.service';
import { FormBuilder, FormGroup, Validators, } from '@angular/forms';
import { Subject, Subscription } from 'rxjs'
import { tap } from 'rxjs/operators'
import { BehaviorSubject } from 'rxjs';
import { Rol } from 'src/app/core/models/rol.model';
import { NuevoUsuario } from 'src/app/core/models/nuevo-usuario';
import { TokenService } from 'src/app/core/services/token/token.service';

declare var $  : any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  usuarios: Usuario[] ;

  roles: string[];

  id: number;

  form: FormGroup;
  isAdmin = false;
  
  /* deleteUser: Usuario = {
    id: 0,
    nombre: '',
    apellido: '',
    usuario: '',
    email: '',
    estado: '',
    password: '',
    rol: 
      {
        id: 0,
        rol: '',
    } 
    
  }

  */

  subscripcion: Subscription;
  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private tokenService: TokenService,
  ) { 
    //this.getAllRoles()
    this.buildForm();


  }


  ngOnInit() {
    
   
  //  this.getAllRoles();


  }
/* 
  getAllRoles(): void {
    this.usuarioService.getAllRoles().subscribe(dato => {
      this.roles= dato;
      console.log(dato)
    });
  }
 */




  getAllUsuarios(): void {
    this.usuarioService.getAllUsuarios().subscribe(dato => {
      this.usuarios = dato;
      this.usuarios = dato.filter((usuario) => usuario.estado === 'A');
      console.log(dato)
    });
  }

 /*  deleteUsuario(id: number) {
 

    this.usuarioService.deleteUsuario(id, this.deleteUser).subscribe((dato) => {
      console.log(dato)
      this.getAllUsuarios()
     
    });

  
  } */

 /*  createUser2(event: Event) {
    event.preventDefault();
    if (this.form.valid) {
      const user = this.form.value;
      this.usuarioService.createUsuario(user).subscribe((newUser) => {
        this.usuarios.push(user as Usuario); 
        $("#exampleModalNew").modal('hide');
        console.log('This is the user: ', newUser);
      });
    }

  } */

  private buildForm() {
    this.form = this.formBuilder.group({

      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required,]],
      estado: ['', [Validators.required]],
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
      usuario: ['', [Validators.required]],
      rol:['', [Validators.required]]
    });
  }

}
