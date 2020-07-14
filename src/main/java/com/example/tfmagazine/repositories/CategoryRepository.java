package com.example.tfmagazine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfmagazine.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
