package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    // HQL3: Get attempt with all related data using fetch joins
    @Query(value = "SELECT a FROM Attempt a " +
            "join fetch a.user u " +
            "join fetch a.attemptQuestionList aq " +
            "join fetch aq.question q " +
            "join fetch aq.attemptOptionList ao " +
            "join fetch ao.option op " +
            "WHERE u.id = :userId AND a.id = :attemptId")
    Attempt getAttempt(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
