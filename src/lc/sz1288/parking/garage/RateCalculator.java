package lc.sz1288.parking.garage;

import java.util.Currency;

public interface RateCalculator {
    Currency getParkingFee(Reservation reservation);
}
