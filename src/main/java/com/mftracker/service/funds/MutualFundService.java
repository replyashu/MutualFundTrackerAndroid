package com.mftracker.service.funds;

import com.mftracker.entity.MutualFunds;
import com.mftracker.entity.User;

import java.util.List;
import java.util.Optional;


public interface MutualFundService {

    public void saveMutualFunds(MutualFunds mutualFunds);

    public int saveAllMutualFunds(List<com.mftracker.entity.MutualFunds> mutualFunds);

    public User findUserByMutualFundSchemeName(String email);

    public Optional<MutualFunds> findByUMutualFundSchemeId(String id);

    public List<MutualFunds> findAllMutualFunds();
}
