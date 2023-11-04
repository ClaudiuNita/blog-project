import { Component } from '@angular/core';
import {Router} from "@angular/router";
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'blog';

  isLoggedIn = false;

  constructor(private router: Router, private userService: UserService) {
  }

   ngOnInit() {
      this.router.events.subscribe(event => {
        if (event.constructor.name === "NavigationEnd") {
         this.userService.getUsername().subscribe(
          user => this.isLoggedIn = (user.info == 'user')? true:false 
         );
        }
      })
   }

  goAcasa(){
    this.router.navigate(['/acasa']);
  }
}
