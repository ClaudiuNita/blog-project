import { Component, OnInit } from '@angular/core';
import { UserDetails } from 'src/app/models/UserDetails';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  userDetails: UserDetails | undefined;
  isAdmin = this.appComponent.isAdmin;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private appComponent: AppComponent
  ) { }

  ngOnInit(): void {
    this.getUserDetails();
  }

  getUserDetails(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!);
    this.userService.getUserDetails(id)
      .subscribe(userD => this.userDetails = userD);
  }

  updateUserDetails(): void {
    if(this.userDetails)
      this.userService.updateUserDetails(this.userDetails)
        .subscribe(() => window.location.reload())
  }
}
