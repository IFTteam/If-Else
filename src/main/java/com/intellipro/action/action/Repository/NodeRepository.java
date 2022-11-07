package com.intellipro.action.action.Repository;

import com.intellipro.action.action.entity.Journey;
import com.intellipro.action.action.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
}
