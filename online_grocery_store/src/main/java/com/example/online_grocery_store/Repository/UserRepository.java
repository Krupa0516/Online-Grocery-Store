package com.example.online_grocery_store.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_grocery_store.Entity.UserDtls;


public interface UserRepository extends JpaRepository<UserDtls, Integer> {

	public boolean existsByEmail(String email);

	public UserDtls findByEmail(String email);

    public UserDtls findByEmailAndPhonenum(String email,String phonenum);
}
