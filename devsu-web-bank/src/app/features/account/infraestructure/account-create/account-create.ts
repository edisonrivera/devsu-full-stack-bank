import {Component, inject, output, signal} from '@angular/core';
import {form, FormField, min, pattern, required} from '@angular/forms/signals';
import {AccountPort} from '@features/account/domain/ports/account.port';
import {ErrorResponse} from '@shared/apis/common/response/error.response';

@Component({
  selector: 'app-account-create',
  imports: [FormField],
  templateUrl: './account-create.html',
})
export class AccountCreate {
  readonly #accountPort = inject(AccountPort);

  readonly closed = output<void>();
  readonly created = output<void>();

  readonly submitting = signal(false);
  readonly serverError = signal<string | null>(null);

  readonly accountModel = signal<AccountCreateRequest>({
    accountType: '',
    amount: 0,
    identification: '',
  });

  readonly accountForm = form(this.accountModel, (s) => {
    required(s.accountType, {message: 'Tipo de cuenta es requerido'});

    required(s.amount, {message: 'Monto es requerido'});
    min(s.amount, 0, {message: 'Monto debe ser mayor o igual a 0'});

    required(s.identification, {message: 'Identificación es requerida'});
    pattern(s.identification, /^[a-zA-Z0-9]{5,13}$/, {message: 'Identificación inválida (5–13 alfanuméricos)'});
  });

  onSubmit(): void {
    if (this.accountForm().invalid()) return;

    this.submitting.set(true);
    this.serverError.set(null);

    const f = this.accountModel();
    this.#accountPort.createAccount({
      accountType: f.accountType,
      amount: f.amount,
      identification: f.identification,
    }).subscribe({
      next: () => {
        this.submitting.set(false);
        this.created.emit();
        this.onClose();
      },
      error: (err: any) => {
        this.submitting.set(false);
        const body = err?.error ?? err;
        this.serverError.set((body as ErrorResponse)?.message ?? 'Error inesperado');
      },
    });
  }

  onClose(): void {
    this.accountModel.set({accountType: '', amount: 0, identification: ''});
    this.serverError.set(null);
    this.closed.emit();
  }
}
