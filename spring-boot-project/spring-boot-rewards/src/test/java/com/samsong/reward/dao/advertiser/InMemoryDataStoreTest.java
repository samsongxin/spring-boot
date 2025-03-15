package com.samsong.reward.dao.advertiser;

import com.samsong.reward.modules.Advertiser;
import com.samsong.reward.modules.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InMemoryDataStoreTest {

    private InMemoryDataStore target;
    private Advertiser advertiser1;
    private Advertiser advertiser2;

    @BeforeEach
    void init(){
        target = new InMemoryDataStore();
        advertiser1 = new Advertiser("id1", "Lexus", "john@toyota.com");
        advertiser2 = new Advertiser("id2", "Ford", "ken@ford.com");
    }

    @Test
    public void testGetCampaigns(){
        target.addCampaign(advertiser1, new Campaign("id1", "Ford end of year sell off"));
        target.addCampaign(advertiser2, new Campaign("id3", "VW end of year sell off"));
		advertiser1 =  new Advertiser("id1", "Lexus", "Tom@toyota.com");
		target.addCampaign(advertiser1, new Campaign("id2", "Lexus end of year sell off"));
        Set<Campaign> campaigns = target.getCampaigns(advertiser1);
        assertEquals(2, campaigns.size());
    }

	@Test
	public void testDeleteAdvertiser(){
		target.addCampaign(advertiser1, new Campaign("id1", "Ford end of year sell off"));
		target.addCampaign(advertiser2, new Campaign("id3", "VW end of year sell off"));
		advertiser1 =  new Advertiser("id1", "Lexus", "Tom@toyota.com");
		target.removeAdvertiser(advertiser1);
		assertNull(target.getCampaigns(advertiser1));
	}
}
