import {Component} from '@angular/core';
import {RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';

interface NavItem {
  readonly label: string;
  readonly route: string;
}

@Component({
  selector: 'app-dashboard',
  imports: [
    RouterLinkActive,
    RouterOutlet,
    RouterLink
  ],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  readonly navItems: NavItem[] = [
    {label: 'Clientes', route: '/clientes'},
    {label: 'Cuentas', route: '/cuentas'},
    {label: 'Movimientos', route: '/movimientos'},
    {label: 'Reportes', route: '/reportes'},
  ];
}
