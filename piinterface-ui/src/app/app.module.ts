import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainMenuComponent } from './pages/main-menu/main-menu.component';
import { IconComponent } from './components/icon/icon.component';
import { PiInstancesComponent } from './pages/pi-instances/pi-instances.component';
import { PinsComponent } from './pages/pins/pins.component';
import { PinGroupsComponent } from './pages/pin-groups/pin-groups.component';
import { ButtonsComponent } from './pages/buttons/buttons.component';
import { PageTitleComponent } from './components/page-title/page-title.component';
import { MenuButtonComponent } from './components/menu-button/menu-button.component';
import { ListComponent } from './components/list/list.component';
import { PushbuttonComponent } from './components/pushbutton/pushbutton.component';
import { HttpClientModule } from '@angular/common/http';
import { DialogComponent } from './components/dialog/dialog.component';
import { TextFieldComponent } from './components/text-field/text-field.component';
import { LoadingWheelComponent } from './components/loading-wheel/loading-wheel.component';
import { LoadingModalComponent } from './components/loading-modal/loading-modal.component';
import { MsgboxComponent } from './components/msgbox/msgbox.component';
import { ServiceableMsgboxComponent } from './components/serviceable-msgbox/serviceable-msgbox.component';
import { ToastComponent } from './components/toast/toast.component';
import { ServiceableToastComponent } from './components/serviceable-toast/serviceable-toast.component';
import { LoadingLogComponent } from './components/loading-log/loading-log.component';
import { ServiceableLoadingLogComponent } from './components/serviceable-loading-log/serviceable-loading-log.component';
import { DataTableComponent } from './components/data-table/data-table.component';
import { CrudTableComponent } from './components/crud-table/crud-table.component';
import { DropdownListComponent } from './components/dropdown-list/dropdown-list.component';
import { SwitchComponent } from './components/switch/switch.component';
import { CalendarComponent } from './pages/calendar/calendar.component';

@NgModule({
  declarations: [
    AppComponent,
    MainMenuComponent,
    IconComponent,
    PiInstancesComponent,
    PinsComponent,
    PinGroupsComponent,
    ButtonsComponent,
    PageTitleComponent,
    MenuButtonComponent,
    ListComponent,
    PushbuttonComponent,
    DialogComponent,
    TextFieldComponent,
    LoadingWheelComponent,
    LoadingModalComponent,
    MsgboxComponent,
    ServiceableMsgboxComponent,
    ToastComponent,
    ServiceableToastComponent,
    LoadingLogComponent,
    ServiceableLoadingLogComponent,
    DataTableComponent,
    CrudTableComponent,
    DropdownListComponent,
    SwitchComponent,
    CalendarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
