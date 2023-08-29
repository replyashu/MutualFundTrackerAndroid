package com.mftracker.repository;

import com.mftracker.entity.MutualFund;
import com.mftracker.entity.MutualFundWithoutNav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundsWithoutNavRepository extends JpaRepository<MutualFundWithoutNav, String> {

}
