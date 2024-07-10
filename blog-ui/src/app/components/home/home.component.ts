import { Component, OnInit } from '@angular/core';
import { FormControl } from "@angular/forms";
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user?: User;
  form = new FormControl('');
  error: any;

  constructor(private userService: UserService,
              private appComponent: AppComponent) { }

  ngOnInit(): void {
  }

  getUser(): void {
    // @ts-ignore
    this.userService.getUser(this.form.value? this.form.value:null).subscribe(
      user => {
          this.user = user
          this.error = ''
       },

      err => {
          console.log(err);
          if(this.appComponent.isLoggedIn) {
            this.error = err.statusText;
          } else {
            this.error = "User not logged in!";
          }
          this.user = undefined;
      }
    );
  }
}
