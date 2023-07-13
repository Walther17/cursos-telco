import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [

  {
    path : '',
    component: LayoutComponent,

    children: [
      {
        path: 'index',
        loadChildren: () => import('./index/index.module').then(m =>  m.IndexModule),

      },
   
      {
        path: 'login',
        loadChildren: () => import('./login/login.module').then(m =>  m.LoginModule),

      },

      {
        path: 'register',
        loadChildren: () => import('./register/register.module').then(m =>  m.RegisterModule),

      },
      {
        path: 'lista',
        loadChildren: () => import('./producto/producto.module').then(m =>  m.ProductoModule),

      },
  

    ]
    ,

    


  },

  


  {
    path: '**',
    loadChildren: () => import('./page-not-found/page-not-found.module').then(m =>  m.PageNotFoundModule), 

  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
