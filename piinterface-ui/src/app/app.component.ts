import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingLogService } from './components/services/loading-log/loading-log.service';
import { BackendNotification } from './model/BackendNotification';
import { DataService } from './services/data-service/data-service.service';
import { getCookie, setCookie } from './utils/cookies';
import { getSkinName } from './utils/skin-utils';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'piinterface-ui';

  themeState : boolean = false;
  themeName : string = "light";

  constructor(
    dataService : DataService,
    loadingLogService : LoadingLogService,
    public router : Router
  ) {
    this.getThemeFromCookie();
    this.applyTheme();

    loadingLogService.setProperties(
      // Acquire function
      () => dataService.notificationsRepository.getCustomOperation("findAll"),

      // Icon function
      (msg:BackendNotification) => {
        // Info
        let iconName : string = "i";

        // Warning
        if (msg && msg.type == 1) {
          iconName = "exclamation";
        }

        // Error
        if (msg && msg.type == 2) {
          iconName = "x";
        }

        // Complete path/name
        return "assets/skins/" + getSkinName() + "/icons/" + iconName + ".png";
      },

      // Class function
      (msg:BackendNotification) => {
        let className = "info";
        if (msg && msg.type == 1) {
          className = "warning";
        }

        if (msg && msg.type == 2) {
          className = "error";
        }

        return className;
      },

      // Title function
      (msg:BackendNotification) => "",

      // Description function
      (msg:BackendNotification) => msg ? msg.message : "",

      // Polling interval
      1000
    );
  }

  onThemStateChanged() {
    this.themeName = this.themeState ? "dark" : "light";
    this.applyTheme();
  }

  private getThemeFromCookie() {
    this.themeName = getCookie("themeName");

    if (this.themeName == undefined) {
      this.themeName = "dark";
    }

    this.themeState = this.themeName == "light" ? false : true;
  }

  private applyTheme() {
    document.body.classList.remove("light");
    document.body.classList.remove("dark");
    document.body.classList.add(this.themeName);
    setCookie("themeName", this.themeName);
  }
}
