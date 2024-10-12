package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryConfiguration extends JpaRepository<Configuration, Long> {
}
