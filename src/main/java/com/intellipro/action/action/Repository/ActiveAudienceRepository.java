package com.intellipro.action.action.Repository;

import com.intellipro.action.action.entity.ActiveAudience;
import com.intellipro.action.action.entity.AudienceActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import static org.hibernate.loader.Loader.SELECT;
@Repository
public interface ActiveAudienceRepository extends JpaRepository<ActiveAudience, Long> {
    @Query(value="SELECT a FROM ActiveAudience a WHERE a.AudienceId=:audienceID")
    Optional<ActiveAudience> searchActiveAudienceByAudienceId(long audienceID);
}
