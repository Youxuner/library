import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthorService } from '../services/author.service';
import { Author } from '../types/Author';

@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.css']
})
export class AuthorsComponent implements OnInit {
  private authorService: AuthorService;
  private router: Router;
  public authors = [];

  constructor(authorService: AuthorService, router: Router) {
    this.authorService = authorService;
    this.router = router;
  }

  ngOnInit(): void {
    this.authors = this.authorService.getAuthors();
  }

  public infoAuthor(author: Author) {
    this.router.navigate(["authors", author.idAutore]);
  }

}
