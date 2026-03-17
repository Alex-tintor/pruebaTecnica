import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Transaccion } from '../../modelos/Transaccion.modelo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TransaccionesService {
    private http = inject(HttpClient);
  private api = 'http://localhost:8080/api/v1/transacciones';

  obtenerTodas(): Observable<Transaccion[]> {
    return this.http.get<Transaccion[]>(this.api);
  }

  crear(payload: any): Observable<Transaccion> {
    return this.http.post<Transaccion>(this.api, payload);
  }

  anular(payload: any): Observable<Transaccion> {
    return this.http.post<Transaccion>(`${this.api}/anular`, payload);
  }
}
