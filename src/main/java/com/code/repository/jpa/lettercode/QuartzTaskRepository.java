package com.code.repository.jpa.lettercode;

import com.code.repository.entity.QuartzTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartzTaskRepository extends JpaRepository<QuartzTask,String> {
}
