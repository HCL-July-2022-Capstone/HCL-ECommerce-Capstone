import { Component, OnInit, Inject } from '@angular/core';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { AuthState, AuthStateManager, getUserInfo, validateClaims, OktaAuth } from '@okta/okta-auth-js';
import { Observable, filter, map } from 'rxjs';


interface Claim {
  claim: string;
  value: unknown;
}

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  
  claims: Claim[] = [];
  public email$!: Observable<string>; 

  public fname$!: Observable<string>; 

  public firstName: any; 
  public middleName: any;
  public lastName: any;
  public email: any;
  public phoneNum: string | undefined; 
  public street: string | undefined;
  public city: string | undefined;
  public state: string | undefined;
  public zip: string | undefined;

  constructor(private _oktaAuthStateService: OktaAuthStateService,
    @Inject(OKTA_AUTH) private _oktaAuth: OktaAuth ) {}

  async ngOnInit() {

    const userInfo = this._oktaAuth.token.getUserInfo()

    console.log(userInfo)

    this.email$ = this._oktaAuthStateService.authState$.pipe(
      filter(
        (authState: AuthState) => !!authState && !!authState.isAuthenticated
      ),
      map((authState: any) => authState.idToken?.claims.email ?? '')
    );

    const userClaims = await this._oktaAuth.getUser()as any;
    this.claims = Object.entries(userClaims).map(entry => ({ claim: entry[0], value: entry[1] }));

    console.log(userClaims)

    this.firstName = userClaims.given_name
    
    this.lastName = userClaims.family_name
    this.email = userClaims.email

    this.street = userClaims.address.street_address
    this.phoneNum = userClaims.phone_number
    this.city = userClaims.address.locality
    this.state = userClaims.address.region
    this.zip = userClaims.address.postal_code

  }

  //method to save the information from the form and then get passed on to the okta side 
  async save(){

    this.firstName = this.firstName
    this.middleName = this.middleName
    this.lastName = this.lastName
    this.email = this.email 
    this.phoneNum = this.phoneNum
    this.street = this.street
    this.city = this.city
    this.state = this.state 
    this.zip = this.zip


    console.log(this.email)
    console.log(this.street)
    console.log(this.state)
    
    
    await this._oktaAuth.authStateManager.subscribe(this.firstName)

  }


}
