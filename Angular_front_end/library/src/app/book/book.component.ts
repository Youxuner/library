import { Component, Input, OnInit } from '@angular/core';
import { Book } from '../types/Book';

@Component({
  selector: '[app-book]',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  @Input() public book: Book;
  constructor() { }

  ngOnInit(): void {
  }
}
