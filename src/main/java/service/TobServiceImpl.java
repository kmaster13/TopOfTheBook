package service;

import mapper.BidMapper;
import mapper.ResponseMapper;
import model.Bid;
import model.Response;


import java.util.*;

public class TobServiceImpl implements TobService {
    private final TreeMap<Long, Bid> buyStorage = new TreeMap<>();
    private final TreeMap<Long, Bid> saleStorage = new TreeMap<>();

    public String tobLogic(String stdin) {
        Bid currBid = BidMapper.mapStringToBid(stdin);
        TreeMap<Long, Bid> storage;

        if (currBid.getSide() == 'S') {
            storage = saleStorage;
        } else {
            storage = buyStorage;
        }

        switch (currBid.getAction()) {
            case '0' -> {
                changeAmountBid(currBid, storage);
                if (storage.lastKey() <= currBid.getPrice()) {
                    return ResponseMapper.mapBidToResponse(storage.get(currBid.getPrice())).toString();
                }
            }
            case '1', '2' -> {
                if (storage.get(currBid.getPrice()) == null || storage.lastKey() != currBid.getPrice()
                        || storage.get(currBid.getPrice()).getAmount() < currBid.getAmount()) {
                    throw new IllegalArgumentException();
                }
                changeAmountBid(currBid, storage);
                if (storage.isEmpty()) {
                    Response response = new Response(currBid.getInstrument_id(), currBid.getSide(), currBid.getPrice(), 0);
                    if (currBid.getSide() == 'S') {
                        response.setPrice(999999999999999999L);
                    } else {
                        response.setPrice(0);
                    }
                    return response.toString();
                }
                return ResponseMapper.mapBidToResponse(storage.lastEntry().getValue()).toString();
            }
        }
        return "-";
    }

    private void changeAmountBid(Bid bid, TreeMap<Long, Bid> storage) {
        Bid tempBid = storage.get(bid.getPrice());
        if (bid.getAction() == '0') {
            if (tempBid != null) {
                bid.setAmount(bid.getAmount() + tempBid.getAmount());
            }
            storage.put(bid.getPrice(), bid);
        } else {
            int diff = tempBid.getAmount() - bid.getAmount();
            if (diff != 0) {
                tempBid.setAmount(tempBid.getAmount() - bid.getAmount());
                storage.put(bid.getPrice(), tempBid);
            } else {
                storage.remove(bid.getPrice());
            }
        }
    }
}
