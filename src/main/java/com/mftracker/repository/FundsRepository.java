package com.mftracker.repository;


import com.mftracker.entity.MutualFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundsRepository extends JpaRepository<MutualFund, String> {

//    MutualFunds findByMutualFundSchemeName(String fundName);
//
//    @Query(value = "SELECT * FROM mutual_funds u WHERE u.mutualFundsSchemeId = :schemeId",
//            nativeQuery = true)
//    MutualFunds findUser(@Param("schemeId") String schemeId);
//
//    @Query(value = "SELECT * FROM mutual_funds u WHERE u.email = :email LIMIT 1",
//            nativeQuery = true)
//    MutualFunds findByEmailUser(@Param("email") String email);


}
