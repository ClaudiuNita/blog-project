import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { UserDetails } from 'src/app/models/UserDetails';
import { UserDetailsService } from 'src/app/services/userDetails/user-details.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  userDetails: UserDetails | undefined;
  isAdmin = this.appComponent.isAdmin;
  currentUserDetailsId = BigInt(this.appComponent.currentUserDetailsId);

  constructor(
    private userDetailsService: UserDetailsService,
    private route: ActivatedRoute,
    private appComponent: AppComponent
  ) { }

  ngOnInit(): void {
    this.getUserDetails();
  }

  getUserDetails(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!);
    this.userDetailsService.getUserDetails(id)
      .subscribe(userD => this.userDetails = userD);
  }

  updateUserDetails(): void {
    if (this.userDetails)
      this.userDetailsService.updateUserDetails(this.userDetails)
        .subscribe(() => window.location.reload());
  }
}
