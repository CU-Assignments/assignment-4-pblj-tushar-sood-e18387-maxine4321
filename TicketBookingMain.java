class TicketBookingSystem {
    private int availableSeats = 10; 

    public synchronized void bookTicket(String customerType, int seatsToBook) {
        if (availableSeats >= seatsToBook) {
            System.out.println(customerType + " is booking " + seatsToBook + " seat(s).");
            availableSeats -= seatsToBook;
            System.out.println("Seats booked successfully. Remaining seats: " + availableSeats);
        } else {
            System.out.println("Not enough seats for " + customerType + ". Remaining seats: " + availableSeats);
        }
    }
}

class BookingThread extends Thread {
    TicketBookingSystem system;
    int seats;
    String customerType;

    public BookingThread(TicketBookingSystem system, String customerType, int seats) {
        this.system = system;
        this.customerType = customerType;
        this.seats = seats;
    }

    @Override
    public void run() {
        system.bookTicket(customerType, seats);
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        BookingThread vip1 = new BookingThread(system, "VIP 1", 3);
        BookingThread vip2 = new BookingThread(system, "VIP 2", 2);

        BookingThread regular1 = new BookingThread(system, "Regular 1", 5);
        BookingThread regular2 = new BookingThread(system, "Regular 2", 4);

        vip1.setPriority(Thread.MAX_PRIORITY);
        vip2.setPriority(Thread.MAX_PRIORITY);
        regular1.setPriority(Thread.MIN_PRIORITY);
        regular2.setPriority(Thread.MIN_PRIORITY);

        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
    }
}
