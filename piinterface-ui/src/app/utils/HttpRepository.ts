import { EventEmitter } from '@angular/core';
import { HttpClientWrapperService, RequestType } from 'src/app/utils/http-client-wrapper/http-client-wrapper.service';
import { Observable } from 'rxjs';

export class HttpRepository {

    public errorEventEmitter : EventEmitter<any> = new EventEmitter<any>();
  
    constructor(private client : HttpClientWrapperService, baseURL: string, private entityEndpointName : string) {
      client.setBaseUrl(baseURL);
    }
  
    public getCustomOperation(operationName: string, params?: Map<string, string>) : Observable<any> {
      return this.requestCustomOperation(RequestType.GET, operationName, null, params);
    }
  
    public saveCustomOperation(operationName: string, body: any, params?: Map<string, string>) : Observable<any> {
      return this.requestCustomOperation(RequestType.POST, operationName, body, params);
    }
  
    public deleteCustomOperation(operationName: string, body: any, params?: Map<string, string>) : Observable<any> {
      return this.requestCustomOperation(RequestType.DELETE, operationName, body, params);
    }
  
    private requestCustomOperation(
      requestType: RequestType,
      operationName: string,
      body?: any,
      params?: Map<string, string>
    ) : Observable<any>
    {
      return this.client.requestWithErrorHandling(
        requestType,
        this.entityEndpointName + "/" + operationName,
        body,
        this.errorEventEmitter,
        params
      );
    }
  }