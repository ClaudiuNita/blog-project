import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AcasaComponent } from "./acasa/acasa.component";
import {UsersComponent} from "./users/users.component";
import {BlogPostsComponent} from "./blog-posts/blog-posts.component";

const routes: Routes = [
  {path: 'acasa', component: AcasaComponent},
  {path: 'users', component: UsersComponent},
  {path: 'blogposts', component: BlogPostsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
