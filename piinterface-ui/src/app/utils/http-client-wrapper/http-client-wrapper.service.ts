import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { share, filter, catchError } from 'rxjs/operators';




/**
 * used by the request() method
 */
export enum RequestType {
    POST    = 'POST',
    GET     = 'GET',
    DELETE  = 'DELETE',
    PUT     = 'PUT',
    HEAD    = 'HEAD',
    JSONP   = 'JSONP',
    OPTIONS = 'OPTIONS',
    PATCH   = 'PATCH'
}

/**
 * used by the request() method
 */
export enum ResponseType {
    ARRAYBUFFER = 'arraybuffer',
    TEXT        = 'text',
    BLOB        = 'blob',
    JSON        = 'json'
}



/**
 * Wrapper over some of the HttpClient's functionality, to make the calls easier and shorter
 */
// TODO: Consider creating a builder - depends on the complexity of the project
@Injectable({
  providedIn: 'root'
})
export class HttpClientWrapperService {

   /**
     * Base URL which will be pre-pended to all requests
     */
    private baseUrl: string;

    constructor(private httpClient: HttpClient) { }

    public setBaseUrl(val: string) {
        this.baseUrl = val;
    }

    public getBaseUrl(): string {
        return this.baseUrl;
    }


    /**
     * Simplified version of the request from the HttpClient,
     * designed for in-line calls instead of having to instantiate headers and other things
     */
    public request<R>(
        requestType: RequestType,
        url: string,
        body: any,
        params?: Map<string, string>,
        headers?: Map<string, string>,
        responseType?: ResponseType
    ): Observable<R> {
     // Workaround to allow the parameters to be passed to the constructor of HttpRequest
        const requestTypeForced: any = requestType.valueOf();
        const responseTypeForced: any = responseType == null ? 'json' : responseType.valueOf();

     // Build the params (if any)
        let httpParams: HttpParams = null;

        if (params != null) {
            httpParams = new HttpParams();

            params.forEach((value: string, key: string) => {
                httpParams = httpParams.append(key, value);
            });
        }

     // Build the headers (if any)
        let httpHeaders: HttpHeaders = null;

        if (headers != null) {
            httpHeaders = new HttpHeaders();

            headers.forEach((value: string, key: string) => {
                httpHeaders = httpHeaders.append(key, value);
            });
        }

     // Send the request
        return this.httpClient.request<R> (
                            requestTypeForced,
                            this.baseUrl + '/' + url,
                            {
                                body: body,
                                headers: httpHeaders,
                                observe: 'body',
                                params: httpParams,
                                responseType: responseTypeForced,
                                reportProgress: false,
                                withCredentials: true
                            }
                        )
                        .pipe(
                            share()
                         )
                         .pipe(
                            catchError(err => of(err.error))
                         )
                        ;
    }

    public requestWithErrorHandling<R>(
        requestType: RequestType,
        url: string,
        body: any,
        errorEventEmitter: EventEmitter<any>,
        params?: Map<string, string>,
        headers?: Map<string, string>,
        responseType?: ResponseType
    ) : Observable<any>
    {    
        // Make the request
        let obs : Observable<any> = this.request<R>(requestType, url, body, params, headers, responseType);
    
        // Prepare to treat errors (HTTP status 500) in case they are encountered
        obs.subscribe(ret => {
          if (ret) {
            let resp : any = ret;
            if (resp.status != null && resp.status != undefined) {
              if (resp.status != 200) {
                errorEventEmitter.emit(ret); // Emit the error to any other subscriber
              }
            }
          }
        });
    
        // Make sure errors (i.e. HTTP status 500) don't go through the regular flow
        return obs.pipe(filter(resp => resp == null || (resp != null && !(resp.status != null && resp.status != 200 && (<string>(resp.status)).length < 4))));
    }
}
