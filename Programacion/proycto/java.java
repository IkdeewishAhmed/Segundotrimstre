
    public void showOccupiedSeats() {

        boolean anyOccupied = false;

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 20; c++) {
                System.out.println("Occupied seats for screening \"" + code + "\":");
                if (seats[r][c].getOccupied()) {

                    anyOccupied = true;
                    // print seat ID and customer name
                    System.out
                            .println("  Seat " + seats[r][c].getID() + " — " + seats[r][c].getCustomor().getFullName());

                }
            }
        }
        if (!anyOccupied) {
            // print no seats occupied
            System.out.println("No seats occupied yet for screening \"" + code + "\".");
        }
    }