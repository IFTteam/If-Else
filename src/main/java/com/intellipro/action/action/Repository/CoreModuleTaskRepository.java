package com.intellipro.action.action.Repository;

import com.intellipro.action.action.entity.CoreModuleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreModuleTaskRepository extends JpaRepository<CoreModuleTask, Long> {
}
