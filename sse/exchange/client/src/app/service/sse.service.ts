import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SseService {
  
  evs: EventSource;
  private subj = new BehaviorSubject([]);
  constructor() {
    this.evs = new EventSource('//localhost:8081/stream-sse-mvc');
  }
  returnAsObservable() {
    return this.subj.asObservable();
  }
  GetExchangeData() {
    let subject = this.subj;
    if (typeof (EventSource) !== 'undefined') {
      this.evs = new EventSource('//localhost:8081/stream-sse-mvc');
      this.evs.onopen = function (e) {
        console.log('Opening connection.Ready State is ' + this.readyState);
      };
      this.evs.onmessage = function (e) {
        console.log('Message Received.Ready State is ' + this.readyState);
        console.log('id: ' + (<any>e).lastEventId + '; type: ' + e.type + ' data: ' + e.data);
        subject.next(JSON.parse(e.data));
      };
      this.evs.addEventListener("message", function (e) {
        console.log("Message event Received.Ready State is " + this.readyState);
        subject.next(e["data"]);
      });
      this.evs.onerror = function (e) {
        console.log(e);
        if (this.readyState == 0) {
          console.log('Reconnecting');
        }
      }
    }
  }
  stopExchangeUpdates() {
    this.evs.close();
  }

}
