import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LogElementService } from '../services/log-element.service';
import { LogElement } from '../types/LogElement';

@Component({
  selector: 'app-log-element',
  templateUrl: './log-element.component.html',
  styleUrls: ['./log-element.component.css']
})
export class LogElementComponent implements OnInit {

  public toShow: boolean;
  public logs: LogElement[];
  constructor(private service: LogElementService) { }

  ngOnInit(): void {
    this.toShow = false;
  }

  public search(f: NgForm) {
    if (f.invalid)
      return;

    this.logs = this.service.statisticheNelPeriodo(f.form.value.status);
    this.toShow = true;
    console.log(this.logs);
  }

}
