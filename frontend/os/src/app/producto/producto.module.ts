import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductoRoutingModule } from './producto-routing.module';
import { ListaProductoComponent } from './components/lista-producto/lista-producto.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    ListaProductoComponent
  ],
  imports: [
    CommonModule,
    ProductoRoutingModule, SharedModule
  ]
})
export class ProductoModule { }
