import { Component, OnInit } from '@angular/core';
import { PersonService } from '../../services/person.service';
import { Person } from '../../models/person';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.scss']
})
export class AddPersonComponent implements OnInit {

  person = new Person();

  constructor(private personService: PersonService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.personService.addPerson(this.person).subscribe(res => {
      window.location.href = '/persons';
    });
  }

}
