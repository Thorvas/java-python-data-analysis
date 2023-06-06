package com.example.demo.Repositories;


import com.example.demo.DummyObject.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Repository for DummyEntity queries
 */
@Repository
public interface DummyEntityRepository extends JpaRepository<DummyEntity, Long>, JpaSpecificationExecutor<DummyEntity> {

}
