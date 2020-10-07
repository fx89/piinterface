import { EventEmitter } from '@angular/core';
import { LoadingModalService } from 'src/app/components/services/loading-modal/loading-modal.service';
import { ToastService } from 'src/app/components/services/toast/toast.service';
import { HttpClientWrapperService } from 'src/app/utils/http-client-wrapper/http-client-wrapper.service';
import { LoadingModalWrappedHttpRepository } from './LoadingModalWrapedHttpRepository';

export class CRUDLoadingModalWrappedHttpRepository extends LoadingModalWrappedHttpRepository {
    constructor(
        loadingModalService : LoadingModalService,
        toastService : ToastService,
        client : HttpClientWrapperService,
        baseURL: string,
        entityEndpointName : string,
        private entityNameSingular : string,
        private entityNamePlural : string,
        private findByParentOperationName : string,
        private findByParentIdParamName : string
    ) {
        super(loadingModalService, toastService, client, baseURL, entityEndpointName);
    }

    public findAll(
        resultHandler?          : Function,
        loadingModalSubtitle?   : string
    ) : EventEmitter<any> {
        return this.getCustomOperationWithLoadingModal(
            "findAll", null,
            resultHandler,
            "LOADING", loadingModalSubtitle ? loadingModalSubtitle : ("Fetching " + this.entityNamePlural + " from registry")
        );
    }

    public findAllByParentId(
        parentId                : number,
        resultHandler?          : Function,
        loadingModalSubtitle?   : string
    ) {
        return this.getCustomOperationWithLoadingModal(
            this.findByParentOperationName, new Map([[this.findByParentIdParamName, parentId.toString()]]),
            resultHandler,
            "LOADING", loadingModalSubtitle ? loadingModalSubtitle : ("Fetching " + this.entityNamePlural + " from registry")
        );
    }

    public findCustomOperation(
        operationName           : string,
        parentIdParamName       : string,
        parentId                : number,
        resultHandler?          : Function,
        loadingModalSubtitle?   : string
    ) {
        return this.getCustomOperationWithLoadingModal(
            operationName, new Map([[parentIdParamName, parentId.toString()]]),
            resultHandler,
            "LOADING", loadingModalSubtitle ? loadingModalSubtitle : ("Fetching " + this.entityNamePlural + " from registry")
        );
    }

    public save(
        item                    : any,
        resultHandler?          : Function,
        loadingModalSubtitle?   : string
    ) : EventEmitter<any> {
        return this.saveCustomOperationWithLoadingModal(
            "save", item, null,
            resultHandler,
            "SAVING", loadingModalSubtitle ? loadingModalSubtitle : ("Saving " + this.entityNameSingular)
        )
    }

    public delete(
        itemId                  : number,
        resultHandler?          : Function,
        loadingModalSubtitle?   : string
    ) : EventEmitter<any> {
        return this.deleteCustomOperationWithLoadingModal(
            "delete", null, new Map([["id", itemId.toString()]]),
            resultHandler,
            "DELETING", loadingModalSubtitle ? loadingModalSubtitle : ("Deleting " + this.entityNameSingular)
        );
    }
}