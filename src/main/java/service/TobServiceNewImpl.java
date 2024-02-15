package service;

import mapper.BidMapper;
import mapper.ResponseMapper;
import model.Bid;
import model.Response;
import model.Storage;

import java.util.HashMap;
import java.util.TreeMap;


public class TobServiceNewImpl implements TobService {
    private final HashMap<Integer, Storage> storage = new HashMap<>();

    @Override
    public String tobLogic(String stdin) {
        Bid currBid = BidMapper.mapStringToBid(stdin);
        Storage currStorage = storage.getOrDefault(currBid.getInstrument_id(), new Storage());
        boolean isBuy = currBid.getSide() == 'B';

        TreeMap<Long, Bid> currMap;
        if (isBuy) {
            currMap = currStorage.buyStorage;
        } else {
            currMap = currStorage.saleStorage;
        }

        if (currBid.getAction() == '0') {
            Bid tempBid = currMap.get(currBid.getPrice());
            if (tempBid != null) {
                tempBid.setAmount(tempBid.getAmount() + currBid.getAmount());
                currMap.put(currBid.getPrice(), tempBid);
            } else {
                currMap.put(currBid.getPrice(), currBid);
            }
            storage.put(currBid.getInstrument_id(), currStorage);

            if (isBuy) {
                if (currMap.lastKey() <= currBid.getPrice()) {
                    return ResponseMapper.mapBidToResponse(currMap.get(currBid.getPrice())).toString();
                }
            } else {
                if (currMap.lastKey() >= currBid.getPrice()) {
                    return ResponseMapper.mapBidToResponse(currMap.get(currBid.getPrice())).toString();
                }
            }
        } else {
            Bid tempBid = currMap.get(currBid.getPrice());

            if (tempBid == null || //Проверка на существование
                    tempBid.getAmount() < currBid.getAmount()) { //Проверка на кол-во
                throw new IllegalArgumentException();
            }

            int diff = tempBid.getAmount() - currBid.getAmount();
            if (diff != 0) {
                tempBid.setAmount(tempBid.getAmount() - currBid.getAmount());
                currMap.put(currBid.getPrice(), tempBid);
            } else {
                currMap.remove(currBid.getPrice());
            }
            storage.put(currBid.getInstrument_id(), currStorage);

            if (currMap.isEmpty()) {
                Response response = new Response(currBid.getInstrument_id(), currBid.getSide(), currBid.getPrice(), 0);
                if (isBuy) {
                    response.setPrice(0);
                } else {
                    response.setPrice(999999999999999999L);
                }
                return response.toString();
            }
            return ResponseMapper.mapBidToResponse(currMap.lastEntry().getValue()).toString();
        }
        return "-";
    }
}