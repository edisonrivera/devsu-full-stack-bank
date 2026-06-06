import {Component, inject, output, signal} from '@angular/core';
import {form, FormField, max, min, pattern, required} from '@angular/forms/signals';
import {ClientPort} from '@features/client/domain/ports/client.port';
import {ErrorResponse} from '@shared/apis/common/response/error.response';

@Component({
  selector: 'app-client-create',
  imports: [FormField],
  templateUrl: './client-create.html',
  styleUrl: './client-create.css',
})
export class ClientCreate {
  readonly #clientPort = inject(ClientPort);

  readonly closed = output<void>();
  readonly created = output<void>();

  readonly submitting = signal(false);
  readonly serverError = signal<string | null>(null);
  readonly showPassword = signal(false);

  readonly clientModel = signal<ClientCreateRequest>({
    name: '', genre: 'M', age: 0, identification: '',
    address: '', phone: '', password: '',
  });

  readonly clientForm = form(this.clientModel, (s) => {
    required(s.name, {message: 'Nombre es requerido'});
    pattern(s.name, /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]{5,200}$/, {message: 'Nombre es inválido (10–200 letras)'});

    required(s.genre, {message: 'Género es requerido'});
    required(s.genre, {message: 'Género es requerido'});

    required(s.age, {message: 'Edad es requerida'});
    min(s.age, 18, {message: 'Edad mínima es 18'});
    max(s.age, 255, {message: 'Edad máxima es 255'});

    required(s.identification, {message: 'Identificación es requerida'});
    pattern(s.identification, /^[a-zA-Z0-9]{5,13}$/, {message: 'Identificación inválida (5–13 alfanuméricos)'});

    required(s.address, {message: 'Dirección es requerida'});
    pattern(s.address, /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ0-9()\/\-_.,\[\]@ *&$#+]{10,500}$/, {message: 'Dirección es inválida (10–500 caracteres)'});

    required(s.phone, {message: 'Teléfono es requerido'});
    pattern(s.phone, /^[0-9]{10}$/, {message: 'Teléfono inválido (10 dígitos)'});

    required(s.password, {message: 'Contraseña es requerida'});
    pattern(s.password,
      /^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\-_=+\[\]{};:'",.<>?/\\|`~])[A-Za-z0-9!@#$%^&*()\-_=+\[\]{};:'",.<>?/\\|`~]{8,72}$/,
      {message: 'Debe tener 8–72 caracteres, una mayúscula, un número y un carácter especial'});
  });

  onSubmit(): void {
    if (this.clientForm().invalid()) return;

    this.submitting.set(true);
    this.serverError.set(null);

    const f = this.clientModel();
    this.#clientPort.createClient(f).subscribe({
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
    this.clientModel.set({name: '', genre: 'M', age: 0, identification: '', address: '', phone: '', password: ''});
    this.serverError.set(null);
    this.closed.emit();
  }
}
