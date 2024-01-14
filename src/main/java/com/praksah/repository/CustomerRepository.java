package com.praksah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.praksah.entity.CustomerMaster;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerMaster, Integer> {

}
