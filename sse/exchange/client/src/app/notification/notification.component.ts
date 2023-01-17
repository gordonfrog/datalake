import { Component, OnInit } from '@angular/core';
import { SseService } from '../service/sse.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  constructor(private sseService: SseService) { }

  ngOnInit(): void {
    this.sseService.returnAsObservable().subscribe(data => console.log(data));
  }
  GetExchangeData() {
    this.sseService.GetExchangeData();
  }
  stopExchangeUpdates() {
    this.sseService.stopExchangeUpdates();
  }

}
