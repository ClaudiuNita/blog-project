import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/User';
import { UserDetailsService } from '../userDetails/user-details.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl = '/blog';
  private usernameUrl = `${this.baseUrl}/username`;
  
  isLoggedIn = false;
  isAdmin = false;
  currentUserUsername = '';
  currentUserDetailsId = 0;

  constructor(private http: HttpClient, 
              private userDetailsService: UserDetailsService) { }

  getUsername() {
    return this.http.get<User>(this.usernameUrl);
  }

  authUser() {
    this.getUsername().subscribe(
      currentUser => 
      { 
        if (currentUser != null) {
          this.isLoggedIn = true;
          this.isAdmin = currentUser.username === 'admin'? true : false;
          this.currentUserUsername = currentUser.username;
          this.getCurrentUserDetailsId();
        }         
      }
    );  
  }

  getCurrentUserDetailsId() {
    if (this.isLoggedIn && !this.isAdmin) {
      this.userDetailsService.getUserDetailsId(this.currentUserUsername).subscribe(
        id => this.currentUserDetailsId = id
      );
    }
  }
}
