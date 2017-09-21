package com.kavai.zopa.loanmatcher.engine;

import com.kavai.zopa.loanmatcher.engine.exception.MarketLiquidityException;
import com.kavai.zopa.loanmatcher.engine.model.Lender;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class PriorityQueueBasedLenderSelectorTest {

    private PriorityQueueBasedLenderSelector sut;

    @Before
    public void setUp() throws Exception {
        sut = new PriorityQueueBasedLenderSelector();
    }

    @Test
    public void selectBestRateLenders_HappyPath() throws Exception {
        final Lender l1 = new Lender("l1", 1, 10);
        final Lender l2 = new Lender("l2", 2, 10);
        final Lender l3 = new Lender("l3", 3, 20);
        final Lender l4 = new Lender("l4", 3, 30);
        final List<Lender> lenders = asList(l1, l2, l3, l4);
        Collections.shuffle(lenders);

        assertEquals(asList(), sut.selectBestRateLenders(lenders, 0));
        assertEquals(asList(l1), sut.selectBestRateLenders(lenders, 1));
        assertEquals(asList(l1), sut.selectBestRateLenders(lenders, 10));
        assertEquals(asList(l1, l2), sut.selectBestRateLenders(lenders, 11));
        assertEquals(asList(l1, l2), sut.selectBestRateLenders(lenders, 20));
        assertEquals(asList(l1, l2, l4), sut.selectBestRateLenders(lenders, 21));
        assertEquals(asList(l1, l2, l4, l3), sut.selectBestRateLenders(lenders, 51));
    }

    @Test(expected = MarketLiquidityException.class)
    public void selectBestRateLenders_NotEnoughLiquidity() throws Exception {
        final Lender l1 = new Lender("l1", 1, 10);
        sut.selectBestRateLenders(asList(l1), 11);
    }

}