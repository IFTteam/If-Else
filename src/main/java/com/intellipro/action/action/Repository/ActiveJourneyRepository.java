package com.intellipro.action.action.Repository;

import com.intellipro.action.action.entity.ActiveAudience;
import com.intellipro.action.action.entity.ActiveJourney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveJourneyRepository extends JpaRepository<ActiveJourney, Long> {
}
