import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../model/user-model.model";
import {UserServiceService} from "../../service/user-service.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-user-component',
  templateUrl: './user-component.component.html',
  styleUrls: ['./user-component.component.css']
})
export class UserComponentComponent implements OnInit {

  userModel!: UserModel[];
  data: UserModel | undefined;

  constructor(private userService: UserServiceService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  //get all
  getAllUsers(): void {
    this.userService.getAllUsers().subscribe((userModel) => {
      this.userModel = userModel;
    });
  }

  //get by id
  getById(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.userService.getUserId(id)
      .subscribe((data) => this.data = data)
  }

  //update

  //delete
  deleteProductById(userModel: UserModel): void {
    this.userModel = this.userModel.filter((data) => data !== userModel);
    this.userService.deleteUser(userModel.id);
  }

}
