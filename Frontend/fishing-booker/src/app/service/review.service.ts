import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { server } from 'src/app/app-global';
import { UserModel } from 'src/app/model/user-model';
import { ReviewModel } from '../model/review-model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  url = server + 'api/reviews';
  constructor(private _http: HttpClient, private loginService: LoginService) {}

  getClientAvailableReviews() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/available-reviews/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }

  newReview(review: ReviewModel) {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/new-review';
    const headers = this.loginService.getHeaders();
    return this._http.post<any>(url, review, { headers: headers });
  }

  getClientReviewsComplaints() {
    const id = this.loginService.getCurrentUser().id;
    const url = this.url + '/client-reviews-complaints/' + id;
    const headers = this.loginService.getHeaders();
    return this._http.get<any>(url, { headers: headers });
  }
}
