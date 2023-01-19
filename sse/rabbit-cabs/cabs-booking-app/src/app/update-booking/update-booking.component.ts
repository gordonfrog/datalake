import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from "../booking.service"
import { Booking } from '../booking';

@Component({
  selector: 'app-update-booking',
  templateUrl: './update-booking.component.html',
  styleUrls: ['./update-booking.component.css']
})
export class UpdateBookingComponent implements OnInit {

  
  bookingId: string;
  booking: Booking;

  constructor(private route: ActivatedRoute,private router: Router,
    private bookingService: BookingService) { }

  ngOnInit() {
    this.booking = new Booking();

    this.bookingId = this.route.snapshot.params['bookingId'];
    
    this.bookingService.getBooking(this.bookingId)
      .subscribe(data => {
        console.log(data)
        this.booking = data;
      }, error => console.log(error));
  }

  updateBooking() {
    this.bookingService.updateBooking(this.bookingId, this.booking)
      .subscribe(data => console.log(data), error => console.log(error));
    this.booking = new Booking();
    this.gotoList();
  }

  onSubmit() {
    this.updateBooking();    
  }

  gotoList() {
    this.router.navigate(['/bookings']);
  }

}
