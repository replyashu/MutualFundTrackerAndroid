package com.mftracker.service.funds;

import com.mftracker.entity.MutualFund;
import com.mftracker.entity.User;

import java.util.List;
import java.util.Optional;


public interface MutualFundService {

    public void saveMutualFunds(MutualFund mutualFund);

    public int saveAllMutualFunds(List<MutualFund> mutualFunds);

    public User findUserByMutualFundSchemeName(String email);

    public MutualFund findByMutualFundSchemeId(String id);

    public List<MutualFund> findAllMutualFunds();
}
