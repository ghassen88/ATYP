package com.atypikHouse.atypikHouse.controller;


import com.atypikHouse.atypikHouse.model.AppUser;
import com.atypikHouse.atypikHouse.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {
@Autowired
private AccountService accountService;


  @PostMapping( "/register")
    public AppUser register(@RequestBody  RegisterForm userform){
    if(!userform.getPassword().equals(userform.getRepassword()))
      throw new RuntimeException("you must confirm you password");
    AppUser user=accountService.findUserByUsername(userform.getUsername());
    if(user!=null) throw new RuntimeException("this user already exists");
    AppUser appUser=new AppUser();
    appUser.setUsername(userform.getUsername());
    appUser.setPassword(userform.getPassword());
  accountService.saveUser(appUser);
  accountService.addRoleToUser(userform.getUsername(),"USER");
  return appUser;
    }
}
