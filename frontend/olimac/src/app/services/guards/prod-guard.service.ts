import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { TokenService } from '../../services/token/token.service';

@Injectable({
  providedIn: 'root'
})
export class ProdGuardService implements CanActivate {

 
  realRol!: string;

  constructor(
    private tokenService: TokenService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRol = route.data['expectedRol'];
   this.realRol = this.tokenService.isAdmin() ? 'administrador' : (expectedRol.includes('consumidor') ? 'consumidor' : 'creador');
  if (!this.tokenService.isLogged() || expectedRol.indexOf(this.realRol) < 0) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
