package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int instrument_id;
    private char side;
    private long price;
    private int amount;

    @Override
    public String toString() {
        return instrument_id + ";" +
                side + ";" +
                price + ";" +
                amount;
    }
}
