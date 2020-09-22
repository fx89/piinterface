import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainMenuComponent } from './pages/main-menu/main-menu.component';
import { IconComponent } from './components/icon/icon.component';
import { PiInstancesComponent } from './pages/pi-instances/pi-instances.component';
import { PinsComponent } from './pages/pins/pins.component';
import { PinGroupsComponent } from './pages/pin-groups/pin-groups.component';
import { ButtonsComponent } from './pages/buttons/buttons.component';
import { PageTitleComponent } from './components/page-title/page-title.component';

@NgModule({
  declarations: [
    AppComponent,
    MainMenuComponent,
    IconComponent,
    PiInstancesComponent,
    PinsComponent,
    PinGroupsComponent,
    ButtonsComponent,
    PageTitleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
