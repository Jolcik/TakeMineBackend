package com.pkostrzenski.takemine.repository.jpa;

import com.pkostrzenski.takemine.models.Notifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifierJpaRepository extends JpaRepository<Notifier, Integer> {
}
