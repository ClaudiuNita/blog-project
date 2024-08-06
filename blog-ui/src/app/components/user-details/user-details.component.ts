import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserDetails } from 'src/app/models/UserDetails';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserDetailsService } from 'src/app/services/userDetails/user-details.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  userDetails: UserDetails | undefined;

  constructor(private userDetailsService: UserDetailsService,
              private route: ActivatedRoute,
              public authService: AuthenticationService) { }

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

  toBigInt(id: number) {
    return BigInt(id);
  }
}
