import { AfterViewInit, Component, EventEmitter } from '@angular/core';
import { buttonTypeDisplayFunction, pinDisplayFunction, pinGroupTitleFunction } from 'src/app/common/display-functions';
import { DialogButtonSpec } from 'src/app/components/dialog/dialog.component';
import { MsgboxService } from 'src/app/components/services/msgbox/msgbox.service';
import { ToastService } from 'src/app/components/services/toast/toast.service';
import { Icon } from 'src/app/model/Icon';
import { PiInstancePin } from 'src/app/model/PiInstancePin';
import { PinGroup } from 'src/app/model/PinGroup';
import { UiButton } from 'src/app/model/UiButton';
import { UiButtonType } from 'src/app/model/UiButtonType';
import { DataService } from 'src/app/services/data-service/data-service.service';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent implements AfterViewInit {

  isLocked : boolean = true;

  buttonTypes : UiButtonType[] = [];
  pushbuttonType : UiButtonType;
  buttonTypeDisplayFunction : Function = buttonTypeDisplayFunction;

  icons : Icon[] = [];
  selectedIcon : Icon;
  selectedIconStateBeingEdited : number;

  iconNameValidationFunction : Function = (name : string) => (name && name.length >=3) ? "" : "The Icon name must be at least 3 characters long";

  pins : PiInstancePin[] = [];
  pinDisplayFunction : Function = pinDisplayFunction;
  pinSelectionValidationFunction : Function = (item) => item ? "" : "You must select a pin";

  groups : PinGroup[] = [];
  groupDisplayFunction : Function = pinGroupTitleFunction;
  groupSelectionValidationFunction : Function = (item) => item ? "" : "You must select a group";

  buttons : UiButton[] = [];
  selectedButton : UiButton;

  buttonTitleValidationFunction : Function = (title : string) => (title && title.length >=3) ? "" : "The button title must be at least 3 characters long";

  addButtonDialogShowEvent : EventEmitter<any> = new EventEmitter<any>();
  chooseIconDialogShowEvent : EventEmitter<any> = new EventEmitter<any>();
  editIconDialogShowEvent : EventEmitter<any> = new EventEmitter<any>();

  chooseIconDialogCustomButtons : DialogButtonSpec[] = [
    new DialogButtonSpec("Add new", () => {
      this.selectedIcon = new Icon(null, "Name of the new icon", null, null);
      this.editIconDialogShowEvent.emit();
    })
  ];

  constructor(
    private dataService : DataService,
    private toastService : ToastService,
    private msgboxService : MsgboxService
  ) { }

  ngAfterViewInit() {
    this.loadUiButtonTypes().subscribe(() => {
      this.loadUiButtons().subscribe(() => {
        this.loadPins().subscribe(() => {
          this.loadPinGroups().subscribe(() => {
            this.loadIcons();
          });
        });
      });
    });
  }

  loadIcons() : EventEmitter<any> {
    return this.dataService.iconsRepository.findAll((ret) => {
      this.icons = [];
      for (let icon of ret) {
        this.icons[icon.id] = icon;
      }
    });
  }

  loadPins() : EventEmitter<any> {
    return this.dataService.piInstancePinsRepository.findAll((ret) => {
      this.pins = ret;

      if (this.pins && this.pins.length > 1) {
        this.pins = this.pins.sort((p1, p2) => {
          if (p1.piInstance.id == p2.piInstance.id) {
            return p1.id - p2.id
          }
          return p1.piInstance.id - p2.piInstance.id;
        });
      }
    });
  }

  loadPinGroups() : EventEmitter<any> {
    return this.dataService.pinGroupsRepository.findAll((ret) => {
      this.groups = ret;

      if (this.groups && this.groups.length > 1) {
        this.groups = this.groups.sort((g1, g2) => g1.id - g2.id);
      }
    });
  }

  loadUiButtonTypes() : EventEmitter<any> {
    return this.dataService.uiButtonTypesRepository.findAll((ret) => {
      this.buttonTypes = ret;
      this.collectPushbuttonType();
    });
  }

  loadUiButtons() : EventEmitter<any> {
    return this.dataService.uiButtonsRepository.findAll((ret) => {
      this.buttons = ret;
      this.selectedButton = undefined;
    });
  }

  collectPushbuttonType() {
    for (let bt of this.buttonTypes) {
      if (bt.name == 'PIN') {
        this.pushbuttonType = bt;
      }
    }

    if (this.pushbuttonType == null || this.pushbuttonType == undefined) {
      this.toastService.showWarning("Missing button type", "The registry doesn't contain the PIN type");
    }
  }

  onButtonAddButtonClick() {
    if (this.isLocked == false) {
      this.selectedButton = new UiButton(null, "New button", null,this.pushbuttonType, null, null, 0);
      this.addButtonDialogShowEvent.emit();
    }
  }

  onButtonEditButtonClick() {
    if (this.isLocked == false && this.selectedButton) {
      this.addButtonDialogShowEvent.emit();
    }
  }

  onButtonDelButtonClick() {
    if (this.isLocked == false && this.selectedButton) {
      this.msgboxService.showSimpleMsgBox(
        "Confirm deletion",
        "Are you sure you want to delete the selected button?",
        () => {
          this.dataService.uiButtonsRepository.delete(this.selectedButton.id)
          .subscribe(() => {
            this.selectedButton = undefined;
            this.loadUiButtons();
          });
        },
        "Yes", "No"
      );
    }
  }

  onLockingButtonClick() {
    if (this.isLocked) {
      this.isLocked = false;
    } else {
      this.isLocked = true;
      this.selectedButton = undefined;
    }
  }

  onUiButtonClick(button:UiButton) {
    if (this.isLocked == false) {
      this.selectedButton = button;
    } else {
      button.state = 1;
      this.dataService.uiButtonsRepository
        .getCustomOperation("clickButton", new Map([["buttonId", button.id.toString()]]))
        .subscribe(ret => {
          if (ret) {
            button.state = ret.state;
          } else {
            this.toastService.showError("Back-end not working", "NULL received from the back-end while attempting to get the button state");
            button.state = 0;
          }
        });
    }
  }

  onUiButtonTypeChanged() {

  }

  onUiButtonEditDialogSave : Function = () => {
    this.dataService.uiButtonsRepository.save(this.selectedButton).subscribe(() => {
      this.loadUiButtons();
    });
  }

  onUiButtonEditDialogCancel : Function = () => {
    this.selectedButton = undefined;
  }

  onUiButtonIconChangeClick() {
    this.chooseIconDialogShowEvent.emit();
  }

  onUiButtonDialogIconClick() {
    this.selectedButton.state = 1;

    setTimeout(() => {
      this.selectedButton.state = 0;
    }, 1000);
  }

  onChooseIconDialogApply : Function = () => {
    if (this.selectedIcon) {
      this.selectedButton.iconId = this.selectedIcon.id;
    }

    this.selectedIcon = undefined;
  }

  onChooseIconDialogCancel : Function = () => {
    this.selectedIcon = undefined;
  }

  onEditIconIconDialogSave : Function = () => {
    this.dataService.iconsRepository.save(this.selectedIcon).subscribe(() => {
      this.selectedIcon = undefined;
      this.loadIcons();
    });
  }

  onEditIconDialogCancel : Function = () => {
    this.selectedIcon = undefined;
  }

  onIconOffStateSelectButtonClick() {
    this.selectedIconStateBeingEdited = 0;
    document.getElementById('iconChoiceDialog').click();
  }

  onIconOnStateSelectButtonClick() {
    this.selectedIconStateBeingEdited = 1;
    document.getElementById('iconChoiceDialog').click();
  }

  onIconFileChoiceChange(file : File) {
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      if (this.selectedIconStateBeingEdited == 0) {
        this.selectedIcon.stateOff = reader.result.toString();
      }
      if (this.selectedIconStateBeingEdited == 1) {
        this.selectedIcon.stateOn = reader.result.toString();
      }
      this.selectedIconStateBeingEdited = undefined;
    };
    reader.onerror = (err) => {
      this.toastService.showError("Error loading image", err.toString())
    };
  }

  selectIcon(icon:Icon) {
    this.selectedIcon = icon;
  }

  getUiButtonPicture(button : UiButton) : string {
    if (button.iconId > 0) {
      const icon = this.icons[button.iconId];
      if (icon) {
        return button.state == 1 ? icon.stateOn
                                 : icon.stateOff;
      }
    }
    return null;
  }

}
