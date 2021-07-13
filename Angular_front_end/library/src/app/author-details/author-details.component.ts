import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorService } from '../services/author.service';
import { Author } from '../types/Author';
import { Book } from '../types/Book';

@Component({
  selector: 'app-author-details',
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.css']
})
export class AuthorDetailsComponent implements OnInit {
  private service: AuthorService;
  private actRouter: ActivatedRoute;
  private router: Router;
  private _author: Author;
  public books: Book[];

  public set author(author: Author) {
    let a = Object.assign({}, author);
    this._author = a;
  }

  public get author(): Author {
    return this._author;
  }

  constructor(authorService: AuthorService, router: Router, actRouter: ActivatedRoute) {
    this.service = authorService;
    this.router = router;
    this.actRouter = actRouter;
  }

  ngOnInit(): void {
    this.actRouter.params.subscribe((params) => {
      this.author = this.service.getAuthor(params.idAuthor);
      this.books = this.service.getBooksByAuthor(params.idAuthor);
      console.log(this.books);
    })
  }

  public updateAuthor(f: NgForm) {
    if (f.invalid) return;
    this.service.updateAuthor(this.author);
    this.router.navigate(["authors"]);
  }

  public deleteAuthor(event: Event) {
    this.service.deleteAuthor(this.author);
    this.router.navigate(["authors"]);
  }
}
