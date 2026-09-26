package week5.probs;

import java.util.*;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
    public abstract String getCategory();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 150.00;
    }

    @Override
    public String getCategory() {
        return "Regular";
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 250.00;
    }

    @Override
    public String getCategory() {
        return "Premium";
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 400.00;
    }

    @Override
    public String getCategory() {
        return "Recliner";
    }
}

class Show {
    private String showName;
    private boolean started;
    private Set<String> bookedSeats = new HashSet<>();

    public Show(String showName) {
        this.showName = showName;
        this.started = false;
    }

    public String getShowName() {
        return showName;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isSeatAvailable(String seatNumber) {
        return !bookedSeats.contains(seatNumber);
    }

    public boolean bookSeat(String seatNumber) {
        if (bookedSeats.contains(seatNumber)) {
            return false;
        }
        bookedSeats.add(seatNumber);
        return true;
    }

    public void releaseSeat(String seatNumber) {
        bookedSeats.remove(seatNumber);
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private double totalAmount;
    private boolean active;

    private Booking(Customer customer, Show show, List<Seat> seats, double totalAmount) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.totalAmount = totalAmount;
        this.active = true;
    }

    public static Booking createBooking(Customer customer, Show show, List<Seat> requestedSeats) {
        if (requestedSeats == null || requestedSeats.isEmpty() || requestedSeats.size() > 6) {
            System.out.println("Booking must contain between 1 and 6 seats.");
            return null;
        }

        for (Seat s : requestedSeats) {
            if (!show.isSeatAvailable(s.getSeatNumber())) {
                System.out.println("Seat " + s.getSeatNumber() + " is already booked for this show.");
                return null;
            }
        }

        double total = 0.0;
        List<String> seatLabels = new ArrayList<>();
        for (Seat s : requestedSeats) {
            show.bookSeat(s.getSeatNumber());
            total += s.getPrice();
            seatLabels.add(s.getSeatNumber());
        }

        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                customer.getName(), String.join(", ", seatLabels), total);

        return new Booking(customer, show, requestedSeats, total);
    }

    public boolean cancel() {
        if (!active) {
            System.out.println("Booking already cancelled.");
            return false;
        }
        if (show.isStarted()) {
            System.out.println("Cannot cancel booking after the show has started.");
            return false;
        }

        List<String> seatLabels = new ArrayList<>();
        for (Seat s : seats) {
            show.releaseSeat(s.getSeatNumber());
            seatLabels.add(s.getSeatNumber());
        }
        this.active = false;
        System.out.println(customer.getName() + "'s booking cancelled. Seats " + String.join(", ", seatLabels) + " released.");
        return true;
    }
}

public class q3 {
    public static void main(String[] args) {
        Show show7PM = new Show("7 PM Show");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        List<Seat> ashaSeats = Arrays.asList(
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5")
        );
        Booking ashaBooking = Booking.createBooking(asha, show7PM, ashaSeats);

        Booking.createBooking(ravi, show7PM, Collections.singletonList(new RegularSeat("A2")));

        Booking raviBooking = Booking.createBooking(ravi, show7PM, Collections.singletonList(new ReclinerSeat("R1")));

        if (ashaBooking != null) {
            ashaBooking.cancel();
        }

        Booking nehaBooking = Booking.createBooking(neha, show7PM, Collections.singletonList(new RegularSeat("A2")));
    }
}
