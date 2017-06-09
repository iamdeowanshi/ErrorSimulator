import { Component, OnInit } from '@angular/core';
import { ErrorService } from './app.service';
import { Observable } from 'rxjs';

import { Question } from './app.question';
import { Link } from './app.link';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Error Simulator App';
  link : Link;
  error_msg: Question;

  constructor(private errorService: ErrorService) {
   }

  ngOnInit() {
	   this.getRandomError();
   }

   getRandomError() {
        this.errorService.getRandomError()
	   .subscribe(
                data => this.error_msg = data);  
                console.log(this.error_msg.question);
   }

   getSolution(question : Question) {
   	console.log(question.question);
   	this.errorService.getSolution(question)
	   .subscribe(
                data => this.link = data);  
   }
}
