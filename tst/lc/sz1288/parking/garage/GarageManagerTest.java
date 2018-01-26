package lc.sz1288.parking.garage;

import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Currency;
import java.util.Locale;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GarageManagerTest {
    private static final Slot SLOT_1 = new Slot("1", Slot.SlotSize.MEDIUM);
    private static final Slot SLOT_2 = new Slot("2", Slot.SlotSize.SMALL);
    private static final Slot SLOT_3 = new Slot("3", Slot.SlotSize.MEDIUM);
    private static final Slot SLOT_4 = new Slot("4", Slot.SlotSize.MEDIUM);
    private static final Slot SLOT_5 = new Slot("5", Slot.SlotSize.LARGE);
    private static final Set<Slot> SLOTS = ImmutableSet.of(SLOT_1, SLOT_2, SLOT_3, SLOT_4, SLOT_5);
    private static final Car CAR_1 = new Car("asdsa", Car.CarSize.MEDIUM);
    private static final Car CAR_2 = new Car("asdas", Car.CarSize.MEDIUM);
    private static final Car CAR_3 = new Car("ewewq", Car.CarSize.MEDIUM);
    private static final Car CAR_4 = new Car("redgfg", Car.CarSize.MEDIUM);
    private static final Car CAR_5 = new Car("oiuyu", Car.CarSize.MEDIUM);

    @Mock
    private RateCalculator rateCalculator;
    @Mock
    private VisaComponent visaComponent;

    private GarageManager garageManager;

    @Before
    public void setup() {
        garageManager = new GarageManager(SLOTS, rateCalculator, visaComponent);
        when(rateCalculator.getParkingFee(any())).thenReturn(Currency.getInstance(Locale.CANADA));
        when(visaComponent.charge(any())).thenReturn(true);
    }

    @Test
    public void testGarage() throws Exception {
        garageManager.enter(CAR_1);
        garageManager.enter(CAR_2);
        garageManager.enter(CAR_3);
        garageManager.enter(CAR_4);
        garageManager.checkout(CAR_1);
        garageManager.checkout(CAR_2);
        garageManager.checkout(CAR_3);
        garageManager.checkout(CAR_4);

        verify(rateCalculator, times(4)).getParkingFee(any());
        verify(visaComponent, times(4)).charge(any());
    }

    @Test(expected = IllegalStateException.class)
    public void testTooManyCars() throws Exception {
        garageManager.enter(CAR_1);
        garageManager.enter(CAR_2);
        garageManager.enter(CAR_3);
        garageManager.enter(CAR_4);
        garageManager.enter(CAR_5);
    }

    @Test
    public void testSameCar() {
        garageManager.enter(CAR_1);
        garageManager.enter(CAR_1);
    }
}