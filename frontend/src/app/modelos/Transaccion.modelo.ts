export interface Transaccion {
  numeroReferencia: string;
  identificadorTarjeta: string;
  monto: number;
  direccionCompra: string;
  estadoTransaccion: string;
  fechaCreacion: string;
  fechaAnulacion?: string;
}
