import { Component, OnInit, ViewChild } from '@angular/core';
import { RentService } from '../../services/rent.service';
import { ModalDirective } from '../../../../node_modules/angular-bootstrap-md';
import { NgForm } from '@angular/forms';
import { Rent } from '../../models/rent';
import { Person } from '../../models/person';

@Component({
  selector: 'app-rent-list',
  templateUrl: './rent-list.component.html',
  styleUrls: ['./rent-list.component.scss']
})
export class RentListComponent implements OnInit {

  receivedPerson;
  @ViewChild('updateModal') updateModal: ModalDirective;
  @ViewChild('deleteConf') deleteConf: ModalDirective;
  @ViewChild('badInput') badInput: ModalDirective;
  @ViewChild('updateForm') updateForm: NgForm;
  errorMessage: String = 'There was an error filling up the form. Please try again.';
  rents = new Array<Rent>();
  rent = new Rent();
  sortDirection: String = 'asc';
  sortField: String = 'beginRent';
  sortFields: Array<String> = [
    'beginRent',
    'endRent',
    'numberOfDays',
    'finalPrice'
  ];

  constructor(private rentService: RentService) { }

  ngOnInit() {
      this.receivedPerson = JSON.parse(localStorage.getItem('user'));
      this.rentService.getRents(this.receivedPerson.id).subscribe(rents => {
        this.rents = rents;
        for (let i = 0; i < this.rents.length; i++) {
          this.rents[i].numberOfDays = this.rentService.computeDays(this.rents[i]);
          this.rents[i].finalPrice = this.rentService.computePrice(this.rents[i]);
        }
      });
  }

  onDeleteClick(rentId) {
    this.deleteConf.show();
    this.rent.rentId = rentId;
  }

  onSubmit() {
    let cpt;
    if (this.updateForm.value.begin > this.updateForm.value.end) {
      this.errorMessage = 'You must specify valid inputs values for the begin date and the end date. Please try again.';
      this.badInput.show();
    } else {
      this.rent.beginRent = this.updateForm.value.begin;
      this.rent.endRent = this.updateForm.value.end;
      this.rent.numberOfDays = this.rentService.computeDays(this.rent);
      this.rent.finalPrice = this.rentService.computePrice(this.rent);
      this.rentService.updateRent(this.rent.person.id, this.rent).subscribe(res => {
        for (let i = 0; i < this.rents.length; i++) {
          if (this.rents[i].rentId === this.rent.rentId) {
            this.rents.splice(i, 1);
            cpt = i;
          }
        }
        this.rents.splice(cpt, 0, this.rent);
        this.updateModal.hide();
      });
    }
  }

  onEditClick(rent: Rent) {
    this.rent = rent;
    this.updateModal.show();
  }

  onProceed() {
    this.rentService.deleteRent(this.receivedPerson.id, this.rent.rentId).subscribe(res => {
      for (let i = 0; i < this.rents.length; i++) {
        if (this.rents[i].rentId === this.rent.rentId) {
          this.rents.splice(i, 1);
        }
      }
      this.deleteConf.hide();
    });
  }

  onAsc() {
    this.changeStyleAsc();
    this.sortDirection = 'asc';
  }

  onDesc() {
    this.changeStyleDesc();
    this.sortDirection = 'desc';
  }

  changeStyleAsc() {
    const button = document.getElementById('asc');
    const button2 = document.getElementById('desc');
    if (button.click) {
      button.className = 'btn btn-light-green btn-sm';
      button.blur();
      button2.className = 'btn btn-mdb btn-sm';
    }
  }

  changeStyleDesc() {
    const button = document.getElementById('asc');
    const button2 = document.getElementById('desc');
    if (button2.click) {
      button2.className = 'btn btn-light-green btn-sm';
      button2.blur();
      button.className = 'btn btn-mdb btn-sm';
    }
  }
}
