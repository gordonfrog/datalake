
import { Observable } from "rxjs";
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { BookingService } from "../booking.service";
import { BookingDetails } from "../booking-details";

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit {
  bookings : Observable<BookingDetails[]>;
  constructor( private bookingService: BookingService, private router: Router) { }

  ngOnInit() {
    this.reloadBookings();
  }

  reloadBookings(){
    this.bookings = this.bookingService.getBookingList();
  }

  deleteBooking(bookingId: string){
    this.bookingService.deleteBooking(bookingId)
      .subscribe( data => {
        console.log(data);
        this.reloadBookings();
      },
      error => console.log(error))
  }

  updateBooking(id: number){
    this.router.navigate(['update', id]);
  }
}
