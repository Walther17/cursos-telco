import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { ProdGuardService as guard } from './core/services/guardian/prod-guard.service';
import { LoginGuard } from './core/services/guardian/login.guard';
import { IndexComponent } from './index/components/index/index.component';

const routes: Routes = [

  {
    path: '',
    component: LayoutComponent,

    
    children:[
      {
        path: '',
        component: IndexComponent,
      },

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
        path: 'home',

        loadChildren: () => import('./home/home.module').then(m =>  m.HomeModule), canActivate: [guard], data: { expectedRol: ['admin', 'user'] } 
      },

      {
        path: 'lista-productos',
        loadChildren: () => import('./producto/producto.module').then(m =>  m.ProductoModule), canActivate: [guard], data: { expectedRol: ['admin', 'user'] } 
      },

     

     
      
    ],
    
    
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
