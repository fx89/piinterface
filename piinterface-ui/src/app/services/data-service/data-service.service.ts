import { Injectable, EventEmitter } from '@angular/core';
import { HttpClientWrapperService, RequestType } from 'src/app/utils/http-client-wrapper/http-client-wrapper.service';
import { environment } from 'src/environments/environment';
import { LoadingModalService } from 'src/app/components/services/loading-modal/loading-modal.service';
import { CRUDLoadingModalWrappedHttpRepository } from './CRUDLoadingModalWrappedHttpRepository';
import { ToastService } from 'src/app/components/services/toast/toast.service';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  public piInstancesRepository       : CRUDLoadingModalWrappedHttpRepository;
  public ipAddressRangesRepository   : CRUDLoadingModalWrappedHttpRepository;
  public notificationsRepository     : CRUDLoadingModalWrappedHttpRepository;
  public piInstancePinsRepository    : CRUDLoadingModalWrappedHttpRepository;
  public pinOperatingModesRepository : CRUDLoadingModalWrappedHttpRepository;
  public pinGroupsRepository         : CRUDLoadingModalWrappedHttpRepository;
  public pinGroupTypesRepository     : CRUDLoadingModalWrappedHttpRepository;
  public pinGroupPinsRepository      : CRUDLoadingModalWrappedHttpRepository;

  constructor(
    private client : HttpClientWrapperService,
    private loadingModalService : LoadingModalService,
    private toastService: ToastService
  ) { 
    const backendURL = environment.backendAddress;                                                                    //  Endpoint             Singular              Plural                 Get by parent operation  Praent param
    this.piInstancesRepository       = new CRUDLoadingModalWrappedHttpRepository(loadingModalService, toastService, client, backendURL, "piInstances"      , "PI instance"       , "PI instances"       , ""                     , ""            );
    this.ipAddressRangesRepository   = new CRUDLoadingModalWrappedHttpRepository(loadingModalService, toastService, client, backendURL, "ipAddressRanges"  , "IP address range"  , "IP address ranges"  , ""                     , ""            );
    this.notificationsRepository     = new CRUDLoadingModalWrappedHttpRepository(loadingModalService, toastService, client, backendURL, "notifications"    , "Notification"      , "Notifications"      , ""                     , ""            );
    this.piInstancePinsRepository    = new CRUDLoadingModalWrappedHttpRepository(loadingModalService, toastService, client, backendURL, "pins"             , "Pin"               , "Pins"               , "findAllByPiInstanceId", "piInstanceId");
    this.pinOperatingModesRepository = new CRUDLoadingModalWrappedHttpRepository(loadingModalService, toastService, client, backendURL, "pinOperatingModes", "Pin operating mode", "Pin operating modes", ""                     , ""            );
    this.pinGroupsRepository         = new CRUDLoadingModalWrappedHttpRepository(loadingModalService, toastService, client, backendURL, "pinGroups"        , "Pin group"         , "Pin groups"         , ""                     , ""            );
    this.pinGroupTypesRepository     = new CRUDLoadingModalWrappedHttpRepository(loadingModalService, toastService, client, backendURL, "pinGroupTypes"    , "Pin group type"    , "Pin group types"    , ""                     , ""            );
    this.pinGroupPinsRepository      = new CRUDLoadingModalWrappedHttpRepository(loadingModalService, toastService, client, backendURL, "pinGroupPins"     , "Pin group pin"     , "Pin group pins"     , "findAllByPinGroupId"  , "pinGroupId"  );
  }
}

