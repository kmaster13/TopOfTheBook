package mapper;

import model.Bid;

public class BidMapper {
    public static Bid mapStringToBid(String stdin) {
        Bid bid = new Bid();

        String[] args = stdin.split(";");

        bid.setUser_id(args[0]);
        bid.setClorder_id(args[1]);

        if (args[2].length() != 1) {
            throw new IllegalArgumentException("");
        }
        bid.setAction(args[2].charAt(0));

        bid.setInstrument_id(Integer.parseInt(args[3]));

        if (args[4].length() != 1 || !(args[4].equals("B") || args[4].equals("S"))) {
            throw new IllegalArgumentException("");
        }
        bid.setSide(args[4].charAt(0));


        bid.setPrice(Long.parseLong(args[5]));
        bid.setAmount(Integer.parseInt(args[6]));
        bid.setAmount_rest(Integer.parseInt(args[7]));

        return bid;
    }
}
