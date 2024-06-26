import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'blog';

  isLoggedIn = false;
  isAdmin = false;
  currentUserDetailsId = 0;

  constructor(private router: Router, 
              private userService: UserService) {}

  ngOnInit() {
    this.userService.getUsername().subscribe(
      user => 
        {                
          this.isLoggedIn = (user.info !== 'null')? true:false;
          this.isAdmin = (user.info === 'admin')? true:false; 
          this.userService.getUserDetailsId(user.info).subscribe(
              currentUserDetailsId => this.currentUserDetailsId = currentUserDetailsId
          );
        }
    );
  }

  goAcasa(){
    this.router.navigate(['/acasa']);
  }
}
