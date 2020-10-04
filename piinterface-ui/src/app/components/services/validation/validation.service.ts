import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  private validationMessages : string[] = [];

  constructor() { }

  public getValidationMessage(componentId:string) : string {
    return this.validationMessages[componentId];
  }

  public setValidationMessage(componentId:string, validationMessage:string) {
    this.validationMessages[componentId] = validationMessage;
  }

  public clearValidationMessage(componentId:string) {
    this.validationMessages[componentId] = undefined;
  }

  public isComponentValid(componentId:string) : boolean {
    return this.validationMessages[componentId] ? false : true;
  }

  public areComponentsValid(componentIds:string[]) : boolean {
    if (componentIds) {
      for (let componentId of componentIds) {
        if (this.isComponentValid(componentId) == false) {
          return false;
        }
      }
    }

    return true;
  }

  public getFirstValidationMessage(componentIds:string[]) : string {
    if (componentIds) {
      for (let componentId of componentIds) {
        let validationMessage : string = this.getValidationMessage(componentId);

        if (validationMessage) {
          return validationMessage;
        }
      }
    }

    return null;
  }
}
