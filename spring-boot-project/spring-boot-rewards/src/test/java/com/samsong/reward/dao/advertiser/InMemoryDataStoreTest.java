package com.samsong.reward.dao.advertiser;

import com.samsong.reward.modules.Advertiser;
import com.samsong.reward.modules.Campaign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        target.addCampaign(advertiser1, new Campaign("id1", "Lexus end of year sell off"));
        target.addCampaign(advertiser1, new Campaign("id2", "Lexus end of year sell off"));
        target.addCampaign(advertiser2, new Campaign("id3", "Lexus end of year sell off"));
        IntStream.of(1 , 10).forEach(System.out::println);
        Set<Campaign> campaigns = target.getCampaigns(advertiser1);
        assertEquals(2, campaigns.size());
    }
}
