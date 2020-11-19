package com.atypikHouse.atypikHouse.service;


import com.atypikHouse.atypikHouse.dao.AppRoleRepository;
import com.atypikHouse.atypikHouse.dao.AppUserRepository;
import com.atypikHouse.atypikHouse.model.AppRole;
import com.atypikHouse.atypikHouse.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Qualifier("getBCPE")
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
  private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Override
    public AppUser saveUser(AppUser user) {
        String hashPw=bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);
        return appUserRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
  AppRole role=appRoleRepository.findRoleByRoleName(roleName);
  AppUser user=appUserRepository.findUserByUsername(username);
  user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String email) {
        return null;
    }
}
