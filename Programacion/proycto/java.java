
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



         public void findCustomerBySeat(int row, char col){
         int rowIndex = row - 1;
        int colIndex = col - 'A';
    
        if (rowIndex < 0 || rowIndex >= rowSeats || colIndex < 0 || colIndex >= columnSeats) {
            System.out.println("ERROR: Invalid seat position.");
        }else{
            if(seats[rowIndex][colIndex].getOccupied()){
               
                 System.out.println("  Seat " + seats[rowIndex][colIndex].getID() + " is occupied by");
                  System.out
                            .println( seats[rowIndex][colIndex].getCustomor()
 );

            }else{
                
                System.out.println("Seat " + seats[rowIndex][colIndex].getID() + " is empty");
            }
        }

     }


           public int getAvailableSeatsCount(){

        int count = 0;
        for (int r = 0; r < rowSeats; r++) {
            for (int c = 0; c < columnSeats; c++) {
                if (!seats[r][c].getOccupied()) {
                    count++;
                }
            }
        }
       
        return count;
   
      }
      //  in main System.out.println("Available seats: " + screening.getAvailableSeatsCount() + "/200");

      In Proyecciones.java — a new method getMinutesRemaining():
javapublic long getMinutesRemaining(LocalDateTime now) {
    if (now == null || !isInProgress(now)) {
        return -1;
    }
    return java.time.Duration.between(now, endTime).toMinutes();
}
In Proyecciones.toString() — update it to show minutes remaining if in progress:
java@Override
public String toString() {
    String extra = "";
    if (isInProgress(LocalDateTime.now())) {
        extra = " | ⏳ " + getMinutesRemaining(LocalDateTime.now()) + " min remaining";
    }
    return "[" + code + "] " + movieTitle
            + " | Start: " + startTime.format(FORMATTER)
            + " | End: " + endTime.format(FORMATTER)
            + " | Attendees: " + getAttendeeCount()
            + extra;
}


public double getOccupancyPercentage() {
    return (getAttendeeCount() * 100.0) / (rowSeats * columnSeats);
}

System.out.println("Occupied: " + getAttendeeCount() + "/200 | Available: " + getAvailableSeatsCount() + "/200 | Occupancy: " + String.format("%.1f", getOccupancyPercentage()) + "%");