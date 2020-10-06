import { Injectable, EventEmitter } from '@angular/core';
import { HttpClientWrapperService, RequestType } from 'src/app/utils/http-client-wrapper/http-client-wrapper.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PiInstance } from 'src/app/model/PiInstance';
import { HttpRepository } from 'src/app/utils/HttpRepository';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  public piInstancesRepository : HttpRepository;
  public ipAddressRangesRepository : HttpRepository;
  public notificationsRepository : HttpRepository;
  public piInstancePinsRepository : HttpRepository;
  public pinOperatingModesRepository : HttpRepository;

  constructor(
    private client : HttpClientWrapperService
  ) { 
    const backendURL = environment.backendAddress;
    this.piInstancesRepository = new HttpRepository(client, backendURL, "piInstances");
    this.ipAddressRangesRepository = new HttpRepository(client, backendURL, "ipAddressRanges");
    this.notificationsRepository = new HttpRepository(client, backendURL, "notifications");
    this.piInstancePinsRepository = new HttpRepository(client, backendURL, "pins");
    this.pinOperatingModesRepository = new HttpRepository(client, backendURL, "pinOperatingModes");
  }
}

