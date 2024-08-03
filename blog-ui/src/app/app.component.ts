import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { UserService } from './services/user/user.service';
import { UserDetailsService } from './services/userDetails/user-details.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'blog';

  isLoggedIn = false;
  isAdmin = false;
  currentUserUsername = ''
  currentUserDetailsId = 0;

  constructor(private router: Router, 
              private userService: UserService,
              private userDetailsService: UserDetailsService) {}

  ngOnInit() {
    this.userService.getUsername().subscribe(
      currentUser => 
      { 
        if (currentUser != null) {
          this.currentUserUsername = currentUser.username; 
          this.isLoggedIn = true;
          this.isAdmin = (this.currentUserUsername === 'admin')? true : false; 
          if (this.isLoggedIn && !this.isAdmin)
            this.userDetailsService.getUserDetailsId(this.currentUserUsername).subscribe(
              currentUserDetailsId => this.currentUserDetailsId = currentUserDetailsId
            );
        }              
      }
    );
  }

  goAcasa() {
    this.router.navigate(['/acasa']);
  }
}
