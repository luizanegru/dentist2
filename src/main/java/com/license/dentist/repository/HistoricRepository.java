package com.license.dentist.repository;

import com.license.dentist.entities.Historic;
import com.license.dentist.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricRepository  extends JpaRepository<Historic, Integer> {
    @Query(value = "select * from historic where username = :userId", nativeQuery = true)
    Historic getHistoricByUserID(String userId);

    @Query(value = "select case when count(username)> 0 then true else false end from Historic  where username = :userId")
    boolean existsHistoricForUser( String userId);
}
