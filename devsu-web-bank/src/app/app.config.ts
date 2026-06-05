import {ApplicationConfig, provideBrowserGlobalErrorListeners} from '@angular/core';
import {provideRouter} from '@angular/router';
import {APP_ROUTES} from './app.routes';
import {ClientPort} from '@features/client/domain/ports/client.port';
import {ClientApi} from '@features/client/apis/client.api';
import {AccountPort} from '@features/account/domain/ports/account.port';
import {AccountApi} from '@features/account/apis/account.api';
import {MovementPort} from '@features/movement/domain/ports/movement.port';
import {MovementApi} from '@features/movement/apis/movement.api';
import {ReportPort} from '@features/report/domain/ports/report.port';
import {ReportApi} from '@features/report/apis/report.api';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(APP_ROUTES),
    {provide: ClientPort, useClass: ClientApi},
    {provide: AccountPort, useClass: AccountApi},
    {provide: MovementPort, useClass: MovementApi},
    {provide: ReportPort, useClass: ReportApi},
  ]
};
