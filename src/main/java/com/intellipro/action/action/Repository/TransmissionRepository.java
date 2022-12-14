package com.intellipro.action.action.Repository;

import com.intellipro.action.action.entity.Journey;
import com.intellipro.action.action.entity.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Long>  {

    @Query("select t.audience_email from Transmission t where t = ?1")
    long getJourneyId(Transmission transmission);

    @Query("select s from Transmission s where s.journey = ?1")
    List<Transmission> getTransmissionByJourney(Optional<Journey> journey);

}
