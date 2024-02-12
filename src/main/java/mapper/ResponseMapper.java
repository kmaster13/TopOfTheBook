package mapper;

import model.Bid;
import model.Response;

public class ResponseMapper {

    public static Response mapBidToResponse(Bid bid) {
        return new Response(bid.getInstrument_id(), bid.getSide(), bid.getPrice(), bid.getAmount());
    }
}
