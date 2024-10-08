package cinema;
import java.util.Arrays;
import java.util.Scanner;
public class Cinema {

	static int currentIncome;

	public static char printerOfSeats(char[][] seats){
		System.out.println("Cinema:");
		System.out.print("  ");
		for (int i = 0; i <= seats.length; i++) {
			System.out.print((i+1) + " ");
		}
		System.out.println();

		int numberOfRow = 1;
		for(char[] row : seats){
			System.out.print(numberOfRow + " ");
			for ( char number : row){
				System.out.print(number + " ");
			}
			System.out.println();
			numberOfRow++;
		}
		System.out.println();
		return 0;
	}
    public static int buyTicket(int row, int seat, int rows, int seats, char [][] cinemaRoom){

 		int  total=rows*seats;
		int seatPrice;
		if (total <= 60 || row <= rows / 2) {
			seatPrice = 10;
		} else {
			seatPrice = 8;
		}

		System.out.println();
		System.out.println("Ticket price: $" + seatPrice);

		cinemaRoom[row -1 ][seat - 1] = 'B';
		return seatPrice;
	}
	public static double percentage(int numbersOfTickets, int rows, int seats){
		return ((double) numbersOfTickets / (rows * seats)) * 100;
	}
	public static int totalIncome(int rows, int seats){
		int totalSeats = rows * seats;
		int frontRows = rows / 2;
		int backRows = rows - frontRows;

		if (totalSeats <= 60) {
			return totalSeats * 10;
		} else {
			return (frontRows * seats * 10) + (backRows * seats * 8);
		}
	}

	public static boolean wrongInput(char [][] array, int rowNumber, int seatNumber){

        return (rowNumber < 0 || rowNumber > array.length) || (seatNumber < 0 || seatNumber > array[rowNumber - 1].length);
	}

	public static boolean ticketTaken(char [][] array, int rowNumber, int seatNumber){
        return array[rowNumber - 1][seatNumber - 1] == 'B';
	}

    public static void main(String[] args) {
		System.out.println();
        System.out.println("Cinema:");
		
		Scanner input = new Scanner(System.in);
		int rows,seats,option,numbersOfTickets = 0;
		System.out.println("Enter the number of rows:");
		rows = input.nextInt();
		System.out.println("Enter the number of seats in each row:");
		seats = input.nextInt();
		char [][] cinemaRoom = new char[rows][seats];

        for (char[] chars : cinemaRoom) {
            Arrays.fill(chars, 'S');
        }

		do {
			System.out.println("1.Show the seats");
			System.out.println("2.Buy a ticket");
			System.out.println("3.Statistics");
			System.out.println("0.Exit");

			option = input.nextInt();

			switch (option) {
				case 1:
					printerOfSeats(cinemaRoom);
					break;
				case 2:
					while (true) {
						Scanner input2 = new Scanner(System.in);
						System.out.println("Enter a row number:");
						int rowNumber = input2.nextInt();
						System.out.println("Enter a seat number in that row:");
						int seatNumber = input2.nextInt();

						if (wrongInput(cinemaRoom, rowNumber, seatNumber)) {
							System.out.println("Wrong input!");
						} else if (ticketTaken(cinemaRoom, rowNumber, seatNumber)) {
							System.out.println("That ticket has already been purchased!");
						} else {
							buyTicket(rowNumber, seatNumber, rows, seats, cinemaRoom);
							numbersOfTickets++;
							currentIncome +=(buyTicket(rowNumber,seatNumber,rows,seats,cinemaRoom));
							break;
						}
					}
				    break;
                case 3:
					System.out.printf("Number of purchased tickets: %d\n", numbersOfTickets);
					System.out.println();
					System.out.printf("Percentage: %.2f%%", percentage(numbersOfTickets, rows, seats) );
					System.out.println();
					System.out.printf("Current income: $%d",currentIncome);
					System.out.println();
					System.out.printf("Total income: $%d", totalIncome(rows,seats));
					System.out.println();
					break;
				default:
					System.out.println("Option no valid");
					break;
			}
		}while (option != 0);
    }


}