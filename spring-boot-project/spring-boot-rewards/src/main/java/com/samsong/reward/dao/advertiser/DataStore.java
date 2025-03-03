package com.samsong.reward.dao.advertiser;

import com.samsong.reward.modules.Advertiser;
import com.samsong.reward.modules.Campaign;

import java.util.Set;

public interface DataStore {
    void addCampaign(Advertiser advertiser, Campaign campaign);
    Set<Campaign> getCampaigns(Advertiser advertiser);
}
;