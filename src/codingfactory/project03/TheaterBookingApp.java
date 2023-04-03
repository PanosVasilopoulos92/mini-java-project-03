package codingfactory.project03;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  This programme manages the reservations for a theater with 360 seats (12 columns and 30 rows). After it greets the user
 *  it displays for the user a menu with 4 options. First choice is if he/she wants to make a new reservation, second if he/she
 *  wants to cansel an already existed reservation, third if he/she wishes to see the remaining seats and the last option is of
 *  course for exit. All of the above are being accomplished with the help of a boolean 2D Array in witch every element represent
 *  a particular seat inside the theater.
 * @author Panos V.
 * @version 1.0
 */
public class TheaterBookingApp {

    static boolean[][] isSeatBooked = new boolean[12][30];  //The boolean 2D array.
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            displayTheApp();
        } catch (Exception e) {
            System.out.println("Critical error. Program stopped running.");
        }
    }

    /**
     *  This method is responsible for displaying the total amount of the available (unbooked) seats.
     * @return The number of available seats.
     */
    private static int availableSeats() {
        int availableSeats = 0;

        for (int i = 0; i < isSeatBooked.length; i++) {
            for (int j = 0; j < isSeatBooked[i].length; j++) {
                if (!isSeatBooked[i][j]) {
                    availableSeats++;
                }
            }
        }
        return availableSeats;
    }

    /**
     *  This method uses a switch-case statement in order to type cast the character input for the column into an integer,
     *  so that the 2D Array system can work.
     * @param column The typical parameter for the character that represents the theater's column.
     * @return  An integer that will be used to represent the column inside the 2D Array.
     * @throws InputMismatchException
     */
    private static int getColumnNumber(char column) throws InputMismatchException {
        switch (column) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'K':
                return 9;
            case 'L':
                return 10;
            case 'M':
                return 11;
            default:
                throw new InputMismatchException();
        }
    }

    /**
     *  This method contains the algorithms and methods that will allow the programme to manage the booking-reservation
     *  system. It also uses a while loop inside a try-catch for ensuring that the user will provide only the proper data.
     * @throws IOException
     * @throws InputMismatchException
     */
    private static void bookSeat() throws IOException, InputMismatchException {
        char column = ' ';
        int row = 0;
        int castedColumn = 0;

        try{
            while (true) {
                System.out.println("Please enter the column you prefer by typing a letter between A and M (capitals):");
                column = getColumn();
                castedColumn = getColumnNumber(column);
                System.out.println("Now please type the row you prefer by typing a number between 0 and 29:");
                row = getRow();

                if (isSeatBooked[castedColumn][row] == false) {
                    isSeatBooked[castedColumn][row] = true;
                    System.out.printf("The seat you have booked is: %c-%d. Thank you for your reservation.\n", column, row);
                    System.out.println();
                    break;
                } else {
                    System.out.println("Sorry, this seat is already taken. Please choose another one.\n");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input. It's ok, let's try again.");
            bookSeat();
        }
    }

    /**
     * This method contains the algorithms and methods that will allow the programme to manage the cancellations-unbooking
     * system. It also uses a while loop inside a try-catch for ensuring that the user will provide only the proper data.
     *
     * @throws InputMismatchException
     */
    private static void canselSeat() throws InputMismatchException {
        char column = ' ';
        int row = 0;
        int castedColumn = 0;

        try {
            while(true) {
                System.out.println("Please enter the column of your reservation:");
                column = getColumn();
                castedColumn = getColumnNumber(column);

                System.out.println("Please enter the row of your reservation:");
                row = getRow();

                if (isSeatBooked[castedColumn][row] == true) {
                    isSeatBooked[castedColumn][row] = false;
                    System.out.printf("Your reservation (seat: %c-%d) was successfully canceled.\n\n", column, row);
                } else {
                    System.out.println("The seat you want to cancel is already empty.\n");
                }
                break;
            }
        }catch (InputMismatchException e) {
            System.out.println("Wrong input. It's ok, let's try again.");
            canselSeat();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is responsible for greeting the user to the app and displaying the menu every time it is requested.
     */
    private static void displayOptions() {
        System.out.println("Hello and welcome to our Theater reservations App. Please choose one of the \nfollowing option by simply typing the corresponding number (1-4):");
        System.out.println("1. I wanna book a seat.");
        System.out.println("2. I wanna cansel my reservation.");
        System.out.println("3. I wanna see the remaining seats.");
        System.out.println("4. Exit.");
    }

    /**
     *  The purpose of this method is to take the user's decision as for what he/she selected from the menu options and act accordingly.
     *  It uses a switch-case statement in order to achieve this and a while loop for assuring that it will take the proper data in the proper
     *  form in order for program to continue operating-running.
     * @throws IOException
     */
    private static void displayTheApp() throws IOException {
        String userChoice = "";

        do {
            displayOptions();
            System.out.println();
            userChoice = scanner.next();

            switch (userChoice) {
                case "1":
                    bookSeat();
                    break;
                case "2":
                    canselSeat();
                    break;
                case "3":
                    System.out.println("There are "+ availableSeats() + " seats remaining for the show.");
                    System.out.println();
                    break;
                case "4":
                    System.out.println("It was nice to see you. Good bye.");
                    break;
                default:
                    System.out.println("Oups... You typed something wrong. Please select again.\n");
                    break;
            }
        } while (!userChoice.equals("4"));

        scanner.close();
    }

    /**
     *  The method that is responsible for taking from user the character that represents the analogous theater's column.
     * @return The character that user has typed.
     * @throws IOException
     */
    private static char getColumn() throws IOException {
        char column;
        column = (char) System.in.read();
        scanner.nextLine();
        return column;
    }

    /**
     * The method that is responsible for taking from user the number that represents one of the theater's rows. It also contains a
     * while loop in order to ensure that the user will type a valid number.
     * @return The number, provided by the user, that represents a specific row for a specific column of the theater.
     */
    private static int getRow() {
        int row;
        row = scanner.nextInt();

        while(true) {
            if ((row < 0) || (row >= 30)) {
                System.out.println("Wrong input. Please type again the row you prefer: ");
                row = scanner.nextInt();
            } else break;
        }
        return row;
    }
}