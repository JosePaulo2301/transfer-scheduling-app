package com.tokiomarine.scheduling.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokiomarine.scheduling.app.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {}