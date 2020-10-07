import { EventEmitter } from '@angular/core';
import { LoadingModalService } from 'src/app/components/services/loading-modal/loading-modal.service';
import { ToastService } from 'src/app/components/services/toast/toast.service';
import { HttpClientWrapperService } from 'src/app/utils/http-client-wrapper/http-client-wrapper.service';
import { HttpRepository } from 'src/app/utils/HttpRepository';

export class LoadingModalWrappedHttpRepository extends HttpRepository {

    constructor(
        private loadingModalService : LoadingModalService,
        private toastService : ToastService,
        client : HttpClientWrapperService,
        baseURL: string,
        entityEndpointName : string
    ) {
        super(client, baseURL, entityEndpointName);

        this.errorEventEmitter
            .subscribe((err) => {
                this.toastService.showError(err.error, err.message);
                this.loadingModalService.hide();
            });
    }

    public getCustomOperationWithLoadingModal(
        operationName           : string,
        params?                 : Map<string, string>,
        resultHadnler?          : Function,
        loadingModalTitle?      :string,
        loadingModalSubtitle?   :string
    ) : EventEmitter<any>
    {
        return this.loadingModalService.envelopOperation(
            () => this.getCustomOperation(operationName, params),
            resultHadnler,
            loadingModalTitle,
            loadingModalSubtitle
        );
    }

    public saveCustomOperationWithLoadingModal(
        operationName           : string,
        body                    : any,
        params?                 : Map<string, string>,
        resultHadnler?          : Function,
        loadingModalTitle?      :string,
        loadingModalSubtitle?   :string
    ) : EventEmitter<any> {
        return this.loadingModalService.envelopOperation(
            () => this.saveCustomOperation(operationName, body, params),
            resultHadnler,
            loadingModalTitle,
            loadingModalSubtitle
        );
      }
    
      public deleteCustomOperationWithLoadingModal(
        operationName           : string,
        body                    : any,
        params?                 : Map<string, string>,
        resultHadnler?          : Function,
        loadingModalTitle?      :string,
        loadingModalSubtitle?   :string
      ) : EventEmitter<any> {
        return this.loadingModalService.envelopOperation(
            () => this.deleteCustomOperation(operationName, body, params),
            resultHadnler,
            loadingModalTitle,
            loadingModalSubtitle
        );
      }
}