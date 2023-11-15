import { Component, OnInit } from '@angular/core';
import { UserDetails } from '../UserDetails';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  userDetails: UserDetails | undefined;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getUserDetails();
  }

  getUserDetails(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!);
    this.userService.getUserDetails(id)
      .subscribe(userD => this.userDetails = userD);
  }
}
