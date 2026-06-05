import {Routes} from '@angular/router';
import {Dashboard} from '@features/dashboard/dashboard';

export const APP_ROUTES: Routes = [
  {
    path: '',
    component: Dashboard,
    children: [
      {
        path: '',
        redirectTo: 'clientes',
        pathMatch: 'full',
      },
      {
        path: 'clientes',
        loadComponent: () =>
          import('./features/client/client').then(m => m.Client),
        title: 'Clientes — Banco',
      },
      {
        path: 'cuentas',
        loadComponent: () =>
          import('./features/account/account').then(m => m.Account),
        title: 'Cuentas — Banco',
      },
      {
        path: 'movimientos',
        loadComponent: () =>
          import('./features/movement/movement').then(m => m.Movement),
        title: 'Movimientos — Banco',
      },
      {
        path: 'reportes',
        loadComponent: () =>
          import('./features/report/report').then(m => m.Report),
        title: 'Reportes — Banco',
      },
      {
        path: '**',
        redirectTo: 'clientes',
      },
    ],
  },
];
