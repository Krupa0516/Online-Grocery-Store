package com.example.online_grocery_store.Service;

import com.example.online_grocery_store.Entity.UserDtls;

public interface UserService {
    public UserDtls createUser(UserDtls user);

	public boolean checkEmail(String email);

}
