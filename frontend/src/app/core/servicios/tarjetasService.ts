import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tarjeta } from '../../modelos/Tarjeta.modelo';

@Injectable({
  providedIn: 'root',
})
export class TarjetasService {
  private http = inject(HttpClient);
  private api = 'http://localhost:8080/api/v1/tarjetas';

  obtenerTodas(): Observable<Tarjeta[]> {
    return this.http.get<Tarjeta[]>(this.api);
  }

  crear(payload: any): Observable<Tarjeta> {
    return this.http.post<Tarjeta>(this.api, payload);
  }

  entrolar(identificador: string, payload: any): Observable<Tarjeta> {
    return this.http.put<Tarjeta>(`${this.api}/${identificador}/enrolar`, payload);
  }

  inactivar(identificador: string): Observable<void> {
    return this.http.delete<void>(`${this.api}/${identificador}`);
  }
}
