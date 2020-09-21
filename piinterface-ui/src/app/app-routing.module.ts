import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ButtonsComponent } from './pages/buttons/buttons.component';
import { MainMenuComponent } from './pages/main-menu/main-menu.component';
import { PiInstancesComponent } from './pages/pi-instances/pi-instances.component';
import { PinGroupsComponent } from './pages/pin-groups/pin-groups.component';
import { PinsComponent } from './pages/pins/pins.component';


const routes: Routes = [
  { path: 'main-menu', component: MainMenuComponent },
  { path: 'pi-instances', component: PiInstancesComponent},
  { path: 'pins', component: PinsComponent},
  { path: 'pin-groups', component: PinGroupsComponent},
  { path: 'buttons', component: ButtonsComponent},
  { path: '**', component: MainMenuComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
