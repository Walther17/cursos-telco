import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductoRoutingModule } from './producto-routing.module';
import { ListaProductoComponent } from './lista-producto/lista-producto.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ListaProductoComponent
  ],
  imports: [
    CommonModule,
    ProductoRoutingModule, FormsModule, ReactiveFormsModule,

  ]
})
export class ProductoModule { }
