package com.pma.App.dao;

import org.springframework.data.repository.CrudRepository;

import com.pma.App.entities.UserAccount;

public interface UserRepo extends CrudRepository<UserAccount, Long> {

}
