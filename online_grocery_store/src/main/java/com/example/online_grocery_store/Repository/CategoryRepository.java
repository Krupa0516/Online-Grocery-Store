package com.example.online_grocery_store.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.online_grocery_store.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}