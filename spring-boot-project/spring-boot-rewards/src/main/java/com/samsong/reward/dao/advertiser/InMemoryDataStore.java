package com.samsong.reward.dao.advertiser;

import com.samsong.reward.modules.Advertiser;
import com.samsong.reward.modules.Campaign;

import java.util.*;

public class InMemoryDataStore implements DataStore{
    private final Map<Advertiser, Set<Campaign>> inMemoryDB = new HashMap<>();

    @Override
    public void addCampaign(Advertiser advertiser, Campaign campaign) {
        inMemoryDB.putIfAbsent(advertiser, new HashSet<>());
        inMemoryDB.get(advertiser).add(campaign);
    }

    @Override
    public Set<Campaign> getCampaigns(Advertiser advertiser) {
        return inMemoryDB.get(advertiser);
    }

	@Override
	public Set<Campaign> removeAdvertiser(Advertiser advertiser) {
		return inMemoryDB.remove(advertiser);
	}
}
