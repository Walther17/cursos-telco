import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from './components/register/register.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    RegisterComponent, 
  ],
  imports: [
    CommonModule,
    RegisterRoutingModule, SharedModule, FormsModule,
  ]
})
export class RegisterModule { }
