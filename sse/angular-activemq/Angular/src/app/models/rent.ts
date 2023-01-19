import { Person } from './person';
import { Vehicule } from './vehicule';

export class Rent {
    rentId: Number;
    beginRent: Date;
    endRent: Date;
    numberOfDays: Number;
    finalPrice: Number;
    person: Person;
    vehicule: Vehicule;
}
