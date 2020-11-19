package com.atypikHouse.atypikHouse.dao;


import com.atypikHouse.atypikHouse.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findRoleByRoleName(String roleName);
}
