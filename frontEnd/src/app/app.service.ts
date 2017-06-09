import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Headers, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { Question } from './app.question';
import { Link } from './app.link';

const headerDict = {
  'Content-Type': 'application/json',
}

const headerObj = {                                                                                                                                                                                 
  headers: new Headers(headerDict), 
};

@Injectable()
export class ErrorService {
    ask_url = "http://localhost:8080/ask";
    error_url = "http://localhost:8080/random_error";
    
    constructor(private http:Http) { }

    getRandomError(): Observable<Question> {
        return this.http.get(this.error_url, headerObj)
		   .map(this.extractData);
    }

    getSolution( question :Question): Observable<Link> {
        //console.log(question);

    let bodyString = JSON.stringify(question); 
    //console.log(bodyString);
        return this.http.post(this.ask_url, bodyString, headerObj)
           .map(this.extractData);
    }
		
    
    private extractData(res: Response) {
	return res.json();
    }
}
