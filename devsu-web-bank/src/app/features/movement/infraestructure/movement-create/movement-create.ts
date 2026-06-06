import {Component, inject, output, signal} from '@angular/core';
import {form, FormField, max, min, pattern, required} from '@angular/forms/signals';
import {MovementPort} from '@features/movement/domain/ports/movement.port';
import {ErrorResponse} from '@shared/apis/common/response/error.response';
import {MovementCreateRequest} from '@features/movement/domain/models/api/request/movement-create.request';

@Component({
  selector: 'app-movement-create',
  imports: [FormField],
  templateUrl: './movement-create.html',
  styleUrl: './movement-create.css'
})
export class MovementCreate {
  readonly #movementPort = inject(MovementPort);

  readonly closed = output<void>();
  readonly created = output<void>();

  readonly submitting = signal(false);
  readonly serverError = signal<string | null>(null);

  readonly movementModel = signal<MovementCreateRequest>({
    accountNumber: '',
    amount: 0,
  });

  readonly movementForm = form(this.movementModel, (s) => {
    required(s.accountNumber, {message: 'Cuenta es requerida'});
    pattern(s.accountNumber, /^[A-Z0-9\-_]{50}$/, {message: 'Número de cuenta inválido'});

    required(s.amount, {message: 'Monto es requerido'});
    min(s.amount, -1_000_000_000_000, {message: 'Monto tiene un mínimo de -1.000.000.000.000'});
    max(s.amount, 1_000_000_000_000, {message: 'Monto tiene un máximo de 1.000.000.000.000'});
  });

  onSubmit(): void {
    if (this.movementForm().invalid()) return;

    this.submitting.set(true);
    this.serverError.set(null);

    const f = this.movementModel();
    this.#movementPort.createMovement({
      accountNumber: f.accountNumber,
      amount: f.amount,
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
    this.movementModel.set({accountNumber: '', amount: 0});
    this.serverError.set(null);
    this.closed.emit();
  }

  protected readonly Math = Math;
}
