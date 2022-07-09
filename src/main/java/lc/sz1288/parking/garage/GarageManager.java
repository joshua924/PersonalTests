package lc.sz1288.parking.garage;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lc.sz1288.parking.garage.Car.CarSize;
import lc.sz1288.parking.garage.Slot.SlotSize;

public class GarageManager {
    private final Map<CarSize, List<SlotSize>> FEASIBLE_SIZE_MAP = ImmutableMap.of(
            CarSize.SMALL, ImmutableList.of(SlotSize.SMALL, SlotSize.MEDIUM, SlotSize.LARGE),
            CarSize.MEDIUM, ImmutableList.of(SlotSize.MEDIUM, SlotSize.LARGE),
            CarSize.LARGE, ImmutableList.of(SlotSize.LARGE)
    );

    private final RateCalculator rateCalculator;
    private final VisaComponent visaComponent;
    private Map<Car, Reservation> currentReservations;
    private Map<SlotSize, List<Slot>> availableSlots;

    public GarageManager(Set<Slot> slots, RateCalculator rateCalculator, VisaComponent visaComponent) {
        this.currentReservations = new HashMap<>();
        this.availableSlots = slots.stream().collect(Collectors.groupingBy(Slot::getSize));
        this.rateCalculator = rateCalculator;
        this.visaComponent = visaComponent;
    }

    public void enter(Car car) {
        Optional<SlotSize> slotSize = getSmallestSlotSizeForCarSize(car.getSize());
        if (!slotSize.isPresent()) {
            throw new IllegalStateException("Can't enter into the garage, no slots is available");
        }
        List<Slot> availableSlots = this.availableSlots.get(slotSize.get());
        Slot slot = availableSlots.remove(0);
        currentReservations.put(car, new Reservation(slot, car, LocalDateTime.now(), null));
    }

    public void checkout(Car car) {
        Reservation reservation = currentReservations.get(car);
        if (reservation == null) {
            throw new IllegalStateException("This car is not registered in the system");
        }
        reservation.setReservationDuration(Duration.between(reservation.getStartTime(), LocalDateTime.now()));
        Currency parkingFee = rateCalculator.getParkingFee(reservation);
        chargeCustomer(parkingFee);
        Slot slotToBeReleased = reservation.getSlot();
        availableSlots.get(slotToBeReleased.getSize()).add(slotToBeReleased);
    }

    private void chargeCustomer(Currency parkingFee) {
        boolean charged = false;
        while (!charged) {
            charged = visaComponent.charge(parkingFee);
        }
    }

    private Optional<SlotSize> getSmallestSlotSizeForCarSize(CarSize carSize) {
        List<SlotSize> slotSizes = FEASIBLE_SIZE_MAP.get(carSize);
        for (SlotSize size : slotSizes) {
            if (availableSlots.get(size).size() != 0) {
                return Optional.of(size);
            }
        }
        return Optional.empty();
    }
}
