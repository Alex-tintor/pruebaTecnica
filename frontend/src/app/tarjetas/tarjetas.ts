import { Component, inject, OnInit } from '@angular/core';
import { Tarjeta } from '../modelos/Tarjeta.modelo';
import { TarjetasService } from '../core/servicios/tarjetasService';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-tarjetas',
  standalone:true,
  imports: [CommonModule,FormsModule],
  templateUrl: './tarjetas.html',
  styleUrl: './tarjetas.css',
})
export class Tarjetas implements OnInit {
  private tarjetaService = inject(TarjetasService);

  cards: Tarjeta[] = [];
  errorMessage = '';
  successMessage = '';

  createForm = {
    pan: '',
    nombreTitular: '',
    numeroDocumento: '',
    tipoTarjeta: 'DEBITO',
    telefono: ''
  };

  enrollForm = {
    identificador: '',
    numeroDeValidacion: 0
  };

  ngOnInit(): void {
    this.loadCards();
  }

  loadCards(): void {
    this.tarjetaService.obtenerTodas().subscribe({
      next: (data) => this.cards = data,
      error: () => this.errorMessage = 'Error cargando tarjetas'
    });
  }

  createCard(): void {
    this.clearMessages();
    this.tarjetaService.crear(this.createForm).subscribe({
      next: () => {
        this.successMessage = 'Tarjeta creada correctamente';
        this.createForm = {
          pan: '',
          nombreTitular: '',
          numeroDocumento: '',
          tipoTarjeta: 'DEBITO',
          telefono: ''
        };
        this.loadCards();
      },
      error: (err) => this.errorMessage = err?.error?.mensaje || 'Error creando tarjeta'
    });
  }

  enrollCard(): void {
    this.clearMessages();
    this.tarjetaService.entrolar(this.enrollForm.identificador, {
      numeroDeValidacion: this.enrollForm.numeroDeValidacion
    }).subscribe({
      next: () => {
        this.successMessage = 'Tarjeta enrolada correctamente';
        this.enrollForm = { identificador: '', numeroDeValidacion: 0 };
        this.loadCards();
      },
      error: (err) => this.errorMessage = err?.error?.mensaje || 'Error enrolando tarjeta'
    });
  }

  deleteCard(identificador: string): void {
    this.clearMessages();
    this.tarjetaService.inactivar(identificador).subscribe({
      next: () => {
        this.successMessage = 'Tarjeta eliminada correctamente';
        this.loadCards();
      },
      error: (err) => this.errorMessage = err?.error?.mensaje || 'Error eliminando tarjeta'
    });
  }

  private clearMessages(): void {
    this.errorMessage = '';
    this.successMessage = '';
  }
}
