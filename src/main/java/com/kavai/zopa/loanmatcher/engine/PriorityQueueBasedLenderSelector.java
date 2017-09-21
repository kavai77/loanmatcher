package com.kavai.zopa.loanmatcher.engine;

import com.kavai.zopa.loanmatcher.engine.exception.MarketLiquidityException;
import com.kavai.zopa.loanmatcher.engine.model.Lender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class PriorityQueueBasedLenderSelector implements LenderSelector {
    @Override
    public List<Lender> selectBestRateLenders(List<Lender> lenderList, double requestedAmount) throws MarketLiquidityException {
        PriorityQueue<Lender> lenderPriorityQueue = new PriorityQueue<>(new LenderComparator());
        lenderPriorityQueue.addAll(lenderList);
        List<Lender> selectedLenders = new ArrayList<>();
        double reservedAmount = 0;
        while (reservedAmount < requestedAmount) {
            if (lenderPriorityQueue.isEmpty()) {
                throw new MarketLiquidityException();
            }
            final Lender bestRateLender = lenderPriorityQueue.poll();
            selectedLenders.add(bestRateLender);
            reservedAmount += bestRateLender.getAvailable();
        }
        return selectedLenders;
    }

    private static class LenderComparator implements Comparator<Lender> {
        @Override
        public int compare(Lender o1, Lender o2) {
            final int comparedRate = Double.compare(o1.getRate(), o2.getRate());
            final int comparedAmount = -Double.compare(o1.getAvailable(), o2.getAvailable());
            return comparedRate != 0 ? comparedRate : comparedAmount;
        }
    }
}
