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
}
