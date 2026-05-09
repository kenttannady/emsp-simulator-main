package com.github.emsp.simulator.repository;

import org.springframework.stereotype.Repository;

import com.github.emsp.simulator.entity.Request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RequestRepository extends JpaRepository<Request, String>{

    List<Request> findByOrderByDateDesc();

    List<Request> findByUidOrderByDateAsc(String uid);

}
