import { Component, inject } from '@angular/core';
import { TransaccionesService } from '../core/servicios/transaccionesService';
import { Transaccion } from '../modelos/Transaccion.modelo';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-transacciones',
  standalone:true,
  imports: [CommonModule,FormsModule],
  templateUrl: './transacciones.html',
  styleUrl: './transacciones.css',
})
export class Transacciones {
    private transactionService = inject(TransaccionesService);

  transactions: Transaccion[] = [];
  errorMessage = '';
  successMessage = '';

  createForm = {
    identificadorTarjeta: '',
    monto: 0,
    direccionCompra: ''
  };

  annulForm = {
    identificadorTarjeta: '',
    referencia: '',
    totalCompra: 0
  };

  ngOnInit(): void {
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.transactionService.obtenerTodas().subscribe({
      next: (data) => this.transactions = data,
      error: () => this.errorMessage = 'Error cargando transacciones'
    });
  }

  createTransaction(): void {
    this.clearMessages();
    this.transactionService.crear(this.createForm).subscribe({
      next: () => {
        this.successMessage = 'Transacción creada correctamente';
        this.createForm = {
          identificadorTarjeta: '',
          monto: 0,
          direccionCompra: ''
        };
        this.loadTransactions();
      },
      error: (err) => this.errorMessage = err?.error?.mensaje || 'Error creando transacción'
    });
  }

  annulTransaction(): void {
    this.clearMessages();
    this.transactionService.anular(this.annulForm).subscribe({
      next: () => {
        this.successMessage = 'Transacción anulada correctamente';
        this.annulForm = {
          identificadorTarjeta: '',
          referencia: '',
          totalCompra: 0
        };
        this.loadTransactions();
      },
      error: (err) => this.errorMessage = err?.error?.mensaje || 'Error anulando transacción'
    });
  }

  private clearMessages(): void {
    this.errorMessage = '';
    this.successMessage = '';
  }
}
