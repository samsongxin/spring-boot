package com.samsong.reward.dao.advertiser;

import com.samsong.reward.modules.Advertiser;
import com.samsong.reward.modules.Campaign;

import java.util.Set;

public class AWSS3DataStore implements DataStore{

    @Override
    public void addCampaign(Advertiser advertiser, Campaign campaign) {
        //TODO implement
        throw new UnsupportedOperationException("This method needs to be implemented");
    }

    @Override
    public Set<Campaign> getCampaigns(Advertiser advertiser) {
        //TODO implement
        throw new UnsupportedOperationException("This method needs to be implemented");
    }

	@Override
	public Set<Campaign> removeAdvertiser(Advertiser advertiser) {
		//TODO implement
		throw new UnsupportedOperationException("This method needs to be implemented");
	}
}
