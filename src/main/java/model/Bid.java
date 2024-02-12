package model;

import lombok.*;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bid {
    private String user_id;
    private String clorder_id;
    private char action;
    private int instrument_id;
    private char side;
    private long price;
    private int amount;
    private int amount_rest;
}
