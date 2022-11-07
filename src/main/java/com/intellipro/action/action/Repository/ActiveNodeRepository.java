package com.intellipro.action.action.Repository;

import com.intellipro.action.action.entity.ActiveAudience;
import com.intellipro.action.action.entity.ActiveNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface ActiveNodeRepository extends JpaRepository<ActiveNode, Long> {
    @Query(value = "select * from active_node t where t.node_id = node_id",nativeQuery = true)
    ActiveNode findByDBNodeId(@Param("node_id") Long NodeId);

    @Query(value="select t from ActiveNode t where t.id=:Id")
    ActiveNode findByActiveNodeId(Long Id);
}
