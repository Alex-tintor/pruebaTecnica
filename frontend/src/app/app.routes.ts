import { Routes } from '@angular/router';
import { Tarjetas } from './tarjetas/tarjetas';
import { Transacciones } from './transacciones/transacciones';

export const routes: Routes = [
  {path:'',redirectTo:'tarjetas',pathMatch:'full'},
  {path:'tarjetas',component:Tarjetas },
  {path:'transacciones',component:Transacciones}
];
