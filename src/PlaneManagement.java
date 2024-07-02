import java.util.InputMismatchException;
import java.util.Scanner ;


public class PlaneManagement {


    //  Making the scanner class accessible from anywhere in the class by using static keyword.
    //  That way it belongs to the class itself rather than to instance of the class.
    static Scanner input = new Scanner(System.in);


    /* ========================================================== Method : main() ========================================================== */

    /**
     * | METHOD : Main() |
     * @param args
     * @Functionality : Display the Menu Option and allows the user to enter an option
     */
    public static void main(String[] args)
    {

        //  This RaggedArray hold integer values for the seating arrangement.
        int[][] seats_array = new int[4][];
        seats_array[0] = new int[14];
        seats_array[1] = new int[12];
        seats_array[2] = new int[12];
        seats_array[3] = new int[14];

        //  Declaring an RaggedArray to hold Multiple data typed information of the user and the ticket.
        Ticket[][] tickets_array = new Ticket[4][];
        tickets_array[0] = new Ticket[14];
        tickets_array[1] = new Ticket[12];
        tickets_array[2] = new Ticket[12];
        tickets_array[3] = new Ticket[14];


        System.out.println("\n  | WELCOME TO THE PLANE MANAGEMENT APPLICATION ||\n");


        boolean print_menu = true;

        do
        {
            System.out.println("""
                    ****************************************************
                    *                   MENU OPTIONS                   *
                    ****************************************************
                        1) Buy A Seat
                        2) Cancel A Seat
                        3) Find First Available Seat
                        4) Show Seating Plan
                        5) Print Ticket Information & Total Sales
                        6) Search Ticket
                        0) Quit
                    ****************************************************
                    """);

            boolean option_validation = true;

            while (option_validation)
            {
                try
                {
                    System.out.print("SELECT AN OPTION : ");
                    int option = input.nextInt();

                    switch (option)
                    {
                        case 1:
                            /*
                             * Invoking buy_seat() method
                             * seats_array is passed as an argument into the buy_seat() method.
                             * Inside the buy_seat() method, it's possible to access and modify elements of seats_array, bcz of reference (seats_array)
                             */
                            buy_seat(seats_array, tickets_array);
                            break;

                        case 2:
                            //  Invoking cancel_seat() method. seats_array is passed as arguments.
                            cancel_seat(seats_array, tickets_array);
                            break;

                        case 3:
                            //  Invoking find_first_available() method. seat_array is passed as an argument.
                            find_first_available(seats_array);
                            break;

                        case 4:
                            //  Invoking show_first_seat() method. seat_array is passed as an argument.
                            show_seat_planing(seats_array);
                            break;

                        case 5:
                            print_tickets_info(tickets_array);
                            break;

                        case 6:
                            print_tickets(tickets_array);
                            break;

                        case 0:
                            System.out.println("\n***************************************************");
                            System.out.println("\t\t\t.....EXITING SYSTEM.....");
                            System.out.println("***************************************************\n");

                            option_validation = false;
                            print_menu = false;
                            break;

                        default:
                            //  For any Invalid option, the programming will prompt the user until valid option is entered.
                            System.out.println("\n\t▶ Invalid option. Select an option from 1 to 6\n");
                            continue;

                    }
                    option_validation = false;
                }

                catch (InputMismatchException InvalidInput)
                {
                    System.out.println("\n\t▶ Invalid Input for option. Integer required.\n");
                    input.nextLine();
                }
            }
        }
        while (print_menu);
    }

    /* ===================================================================================================================================== */



    /* ======================================================= Method : buy_seat() ========================================================= */


    /**
     * | OPTION : 01 | METHOD : buy_seat() |
     * @Functionality : This method will purchase a seat and make it Unavailable.
     * @Parameter = The PlaceHolder that receive values when the buy_seat() method is called.
     * @param seats_array
     * @param tickets_array
     */
    static void buy_seat(int[][] seats_array, Ticket[][] tickets_array)
    {

        System.out.println("\n\n\n|=================| PURCHASE SEAT |=================|\n");

        //  Invoking both Row letter validation and Seat number validation methods & Saving them in variable to be called upon inside seats arrays occupation checker.

        char row_letter = Valid_RowLetter();

        // row_letter is a parameter in Valid_SeatNumber() method, which is being passed as a return value from Valid_RowLetter(0 method, Bcz its essential in getting a valid seat number.
        int seat_number = Valid_SeatNumber(row_letter);

        /*
         * Using Character Subtraction to get the array indexing.
         * Values of char datatype can perform arithmetic operations. So, row_letter - 'A' : This operation generates an integer value as RowIndex
         */

        //  Checks whether the user entered RowNumber & SeatNumber are already purchased.
        if (seats_array[row_letter - 'A'][seat_number - 1] == 1)
        {

            System.out.println("\n\t| ROW : " + row_letter + " | SEAT : " + seat_number + " | UNAVAILABLE |\n");
            System.out.println("\t▶ Seat already purchased.\n");
        }

        //  Allows user to purchase a validated & available seat.
        else
        {
            System.out.println();

            //  Assigning the invoked Valid_UserDetails() method into a local list UserDetailValidation
            String[] UserDetailValidation = Valid_UserDetails();

            //  Accessing each element of the UserDetailValidation list
            String name = UserDetailValidation[0];
            String surname = UserDetailValidation[1];
            String email = UserDetailValidation[2];


            /*
             * The following conditional statements will assign the price according to the seat category.
             * No need to check in each condition such as; 0 < seat number < 6 , 6 <= seat number <= 9 , 10 <= seat number <= 14 ; Bcz the seat validation is done and called in the above parts of the code.
             */
            double seat_price;

            if (seat_number <= 5){
                seat_price = 200;
            }
            else if (seat_number <= 9) {
                seat_price = 150;
            }
            else {
                seat_price = 180;
            }

            //  Searching through the array for the validated & available seat and purchase it by assigning 1 into that index.
            seats_array[row_letter - 'A'][seat_number - 1] = 1;

            System.out.println("\n\t--------------------------------------------");
            System.out.println("\t| ROW : " + row_letter + " | " + "SEAT : " + seat_number + " | PURCHASE SUCCESSFUL |");
            System.out.println("\t--------------------------------------------\n");

            //  A new Object of the Person class is created with the parameters as follows, and it's assigned to the person_object variable.
            Person person_object = new Person(name, surname, email);

            //  A new Object of the Ticket class is created with parameters as follows (person_object is being passed as an argument into Ticket object).
            Ticket ticket_object = new Ticket(row_letter, seat_number, seat_price, person_object);
            // Invoking SaveFile() method in Tickets class
            ticket_object.SaveFile();

            /*
             * Saving Person Information & Ticket Details inside from the user entered seat in the Tickets Array
             * For each valid Row-Seat, The details of the person who bought that specific seat & details of the ticket will be saved inside the Tickets arrays corresponding row and seat.
             */
            int row = row_letter - 'A';
            int seat = seat_number - 1;

            //  A new Ticket object is created with the following parameters, & it's stored at a specific position in the tickets array.
            tickets_array[row][seat] = ticket_object;

        }

        System.out.println("|===================================================|\n\n\n");

    }

    /* ===================================================================================================================================== */



    /* ====================================================== Method : cancel_seat() ======================================================= */

    /**
     * | OPTION : 02 | METHOD : cancel_seat() |
     * @Functionality : This method cancels a purchased seat and make it available.
     * @param seats_array
     * @param tickets_array
     */
    static void cancel_seat(int[][] seats_array, Ticket[][] tickets_array)
    {

        System.out.println("\n\n\n|==================| CANCEL SEAT |==================|\n");

        //  Invoking both Row letter validation and Seat number validation methods & Saving them in variable to be called upon inside seats arrays occupation checker.

        char row_letter = Valid_RowLetter();

        // row_letter is a parameter in Valid_SeatNumber() method, which is being passed as a return value from Valid_RowLetter(0 method, Bcz its essential in getting a valid seat number.
        int seat_number = Valid_SeatNumber(row_letter);


        /*
         * Using Character Subtraction to get the array indexing
         * In this method instead of reusing the same block of code for separate row_letters, we can subtract characters to get the difference as the index value.
           Bcz, char datatype can perform arithmetic operations.(Character values are represented as Unicode values.)
         */

        /*
         * Checking if the user entered seat is already Available. Bcz, user can't cancel a seat that is already available.
         * Checks the array for RowNumber and SeatNumber to see if its 0.
         */
        if (seats_array[row_letter - 'A'][seat_number - 1] == 0)
        {
            System.out.println("\n\t| ROW : " + row_letter + " | " + "SEAT : " + seat_number + " | NOT PURCHASED |\n");
            System.out.println("\t▶ Seat not sold. Can't be canceled.\n");
        }

        /*
         * Checking if the user entered valid seat is Purchased. Bcz, only purchased seats can be canceled.
         * Searching the array for the Unavailable user entered Seat and Assigning 0 for 1 inorder to make it available.
         */
        else
        {
            seats_array[row_letter - 'A'][seat_number - 1] = 0;

            System.out.println("\n\t------------------------------------------------");
            System.out.println("\t| ROW : " + row_letter + " | SEAT : " + seat_number + " | CANCELLATION SUCCESSFUL |");
            System.out.println("\t------------------------------------------------\n");

            /*
             * Functionality: Deleting Person Information & Ticket information from the user entered Seat in Tickets Array.
             * To cancel the seat number, The Person & Ticket details that has been saved in the Ticket array during the seat purchasing process has to be removed from that specific element of the array.
             * That can be done by making that specific location of the array : NULL
             */

            int row = row_letter - 'A';
            int seat = seat_number - 1;

            tickets_array[row][seat] = null;

            /*
             * Deleting the file that contains Ticket & Person Information
             * Bcz, when a user cancels a seat the file that saved the information of the user & the ticket should be deleted.
             * A Ticket Object is declared in order to invoke the DeleteFile() method which is in the Ticket class.
             */
            Ticket ticket_object = new Ticket(row_letter, seat_number);
            ticket_object.DeleteFile();
        }

        System.out.println("|===================================================|\n\n\n");

    }

    /* ===================================================================================================================================== */



    /* ================================================== Method : find_first_available() ================================================== */

    /**
     * | OPTION : 03 | METHOD : find_first_available() |
     * @param seats_array
     * @Functionality : This method will check for the first available seat in the 2D array.
     */
    static void find_first_available(int[][] seats_array)
    {

        char [] row_letter = { 'A' , 'B' , 'C' , 'D' };

        boolean SeatFound = false;

        for (int row = 0; row < seats_array.length; row++)
        {
            for (int seat = 0; seat < seats_array[row].length; seat++)
            {
                if (seats_array[row][seat] == 0)
                {
                    System.out.println("\n\n\n|==============| FIRST AVAILABLE SEAT |=============|\n");

                    System.out.println("\t\t  | ROW : " + row_letter[row] + " | SEAT : " + (seat + 1) + " | AVAILABLE |\n");

                    System.out.println("|===================================================|\n\n\n");

                    SeatFound = true;
                    return;
                }
            }
        }

        // If no seat is to be found, that means all the seats are purchased.
        if (!SeatFound)
        {
            System.out.println("\n| NO AVAILABLE SEATS FOUND. ALL SEATS BOOKED CURRENTLY. |\n");
        }

    }

    /* ===================================================================================================================================== */



    /* =================================================== Method : show_seat_planning() =================================================== */

    /**
     * | OPTION : 04 | METHOD : show_seat_planning() |
     * @param seats_array
     * @Functionality : This method will display the seat plan for available and unavailable seats.
     */
    static void show_seat_planing(int[][] seats_array)
    {

        System.out.println("\n\n\n|===================| SEAT PLAN |===================|\n");

        char row_letter = 'A';

        System.out.println("\t\t  1  2  3  4  5  6  7  8  9 10 11 12 13 14\n");

        for (int[] row_element : seats_array)
        {
            System.out.print(" ROW " + row_letter + " :  ");

            for (int seat_element : row_element)
            {
                if (seat_element == 0)
                {
                    System.out.print("O  ");
                }
                else
                {
                    System.out.print("X  ");
                }
            }
            System.out.println();
            row_letter++;
        }

        System.out.println("\n|===================================================|\n\n\n");
    }

    /* ===================================================================================================================================== */



    /* =================================================== Method : print_tickets_info() =================================================== */

    /**
     * | OPTION : 05 | METHOD : print_ticket_info() |
     * @param tickets_array
     * @Functionality : This method will display the Information of the tickets that are sold in a session & the total price.
     */

    static void print_tickets_info(Ticket[][] tickets_array)
    {

        System.out.println("\n\n\n|==========| PURCHASED TICKET INFORMATION |=========|\n");

        double total_amount = 0 ;
        int total_seats_booked = 0;

        char row_letter = 'A';

        System.out.println("\t\t  ----------------------------------");
        System.out.println("\t\t  |   ROW   |   SEAT   |   PRICE   |");
        System.out.println("\t\t  ----------------------------------");

        for (Ticket[] row : tickets_array)
        {
            int seat_number = 1;

            for (Ticket ticket : row)
            {
                if (ticket != null)
                {
                    System.out.println("\t\t\t   " + row_letter + "\t\t " + seat_number + "\t\t  $" + ticket.getSeatPrice());
                    total_amount += ticket.getSeatPrice();
                    total_seats_booked += 1;
                }
                seat_number++;
            }
            row_letter++;
        }

        System.out.println("\t\t  ----------------------------------");
        System.out.println("\t\t\t     TOTAL PRICE   =   $" + total_amount);
        System.out.println("\t\t  ----------------------------------\n");

        System.out.println("\t ■ NUMBER OF BOOKED SEATS  =  " + total_seats_booked + "\n");


        for (Ticket[] row : tickets_array)
        {
            for (Ticket ticket : row)
            {
                if (ticket != null)
                {
                    ticket.CustomerDetails();
                }
            }
        }



        System.out.println("\n|===================================================|\n\n\n");

    }

    /* ===================================================================================================================================== */



    /* ====================================================== Method : search_ticket() ===================================================== */

    /**
     * | OPTION : 06 | METHOD : search_ticket() |
     * @param tickets_array
     * @Functionality : This method will display the information of the seat and its person if it's booked. Otherwise, display as available.
     */
    static void print_tickets(Ticket[][] tickets_array)
    {

        System.out.println("\n\n\n|============| SEARCH TICKET INFORMATION |==========|\n");

        char row_letter = Valid_RowLetter();

        int seat_number = Valid_SeatNumber(row_letter);

        //  Assigning the user entered row and seat into seats array and check whether if the values of the element are null.
        Ticket ticket = tickets_array[row_letter - 'A'][seat_number - 1];

        if (ticket != null)
        {
            ticket.TicketInformation();
        }
        else
        {
            System.out.println("\n\t| ROW : " + row_letter + " | SEAT : " + seat_number + " | SEAT AVAILABLE |");
        }


        System.out.println("\n|===================================================|\n\n\n");
    }

    /* ===================================================================================================================================== */



    // ========================================================= Method Validations ========================================================= //


    /*  ------------------------------------------ RowLetter Validation Method -----------------------------------------  */

    /**
     * | METHOD : Valid_RowLetter() |
     * @return row_letter
     * @Functionality : This method validate row_letter.
                        Method continuously prompt the user to enter a row_letter until valid a valid row_letter is provided.
     * @BodyOFMethod :  Saving row_letter in a temporary string variable.Bcz, USER-INPUT length = 1 ; inorder to validate RowLetter.
                        return row_letter : Returns the value of row_letter when the GetValid_RowLetter() method is invoked.
     */
    static char Valid_RowLetter()
    {
        char row_letter;

        while (true)
        {
            System.out.print("\tEnter Row (A/B/C/D) : ");
            String temp_row_letter = input.next().toUpperCase();

            if (temp_row_letter.length() == 1)
            {
                //  Convert string temp_row_letter ==> char row_letter
                row_letter = temp_row_letter.charAt(0);

                if (row_letter != 'A' && row_letter != 'B' && row_letter != 'C' && row_letter != 'D')
                {
                    System.out.println("\t\t▶ Invalid value for Row. Enter again.");
                    continue;
                }
            }
            else
            {
                System.out.println("\t\t▶ Row should be a single character.");
                continue;
            }

            return row_letter;
        }
    }



    /*  ------------------------------------------ SeatNumber Validation Method -----------------------------------------  */

    /**
     * | METHOD : Valid_SeatNumber() |
     * @param row_letter
     * @return seat_number
     * @Functionality : This method will validate the seat_number
                        Method continuously prompts the user to enter a seat_number until a valid seat_number is provided.
     * @BodyOFMethod :  row_letter is passed as a parameter,Even though this method validates the seat_number the row_letter parameter is required bcz it involves in validating seat_number.
     */

    static int Valid_SeatNumber(char row_letter)
    {
        int seat_number;

        while (true)
        {
            try
            {
                System.out.print("\tEnter Seat Number   : ");
                seat_number = input.nextInt();

                if ((row_letter == 'A' || row_letter == 'D') && (seat_number < 1 || seat_number > 14))
                {
                    System.out.println("\t\t▶ Rows A & D seat numbers should be between 1 & 14");
                    continue;
                }

                else if ((row_letter == 'B' || row_letter == 'C') && (seat_number < 1 || seat_number > 12))
                {
                    System.out.println("\t\t▶ Rows B & C seat numbers should be between 1 & 12");
                    continue;
                }

                else
                {
                    break;
                }
            }

            catch (InputMismatchException InvalidInput)
            {
                System.out.println("\t\t▶ Integer required for Seat Number.");
                input.nextLine();
            }
        }
        return seat_number;
    }



    /*  ------------------------------------------ UserDetails Validation Method -----------------------------------------  */


    /**
     * | METHOD : Valid_UserDetail() |
     * @return user_details
     * @Functionality : This method validate User details including firstname , surname , email. Method continuously prompt the user to enter until valid a valid user details are provided.
     * @BodyOFMethod : .isEmpty() = A built-in method that make sure the name is not empty.Will return true if the variable is empty.
     * @BodyOFMethod : .charAt(i) = Retrieve every character of the user input using a for loop. charAt(i) is a built-in method.
     * @BodyOFMethod : Character.isLetter(char_name) = Returns 'false' if character type value has non-alphabetical characters. isLetter() is a method in Character class.
     * @BodyOFMethod : .contains() built-in method checks for what ever arguments that are passed into the .contains() methods and returns true if it contains it.
     * @ImportantPoint : This method returns a 1D array that contains name, surname, email. Instead of returning the values straight away this code block saves it in an array, bcz this method also printout statements within the function when invoked.
     *                   Bcz a method that returns values cannot directly print statements, the name, surname, email are saved into an array.
     *                   That way the array can be returned when the method is invoked and still print the statements. When invoking the method its necessary to extract the elements of the string into local variables in the method where Valid_UserDetails() is being invoked.
     * @Reference : Reference for .isEmpty() ---> <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#isEmpty--">...</a>
     * @Reference : Reference for .charAt(i) ---> <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#charAt-int-">...</a>
     * @Reference : Reference for Character.isLetter(char_name) ---> <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#isLetter-char-">...</a>
     * @Reference : Reference for .contains() ---> <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#contains-java.lang.CharSequence-">...</a>
     */

    static String[] Valid_UserDetails()
    {
        String name;
        String surname;
        String email;

        String[] user_details = new String[3];

        boolean valid_name = false;
        boolean valid_surname = false;
        boolean valid_email = false;

        input.nextLine();

        //  Validates name
        while (!valid_name)
        {
            System.out.print("\tEnter Your Name     : ");
            name = input.nextLine();

            if (name.isEmpty())
            {
                System.out.println("\t\t▶ Name has to be entered.");
                continue;
            }

            for (int i = 0; i < name.length(); i++)
            {
                //  Converting String name ===> char name
                /*  charAt(i) : Retrieving every character of the user input using a for loop. name.charAt(i) built in method is invoked.
                    After each retrieval of a character from the assigned value of name variable, then its being assigned to the char_name variable of char type
                 */
                char char_name = name.charAt(i);

                /*  The retrieved character for each iteration is being validated by the isLetter() built in method of the Character class, to checks for the first value to be found as an invalid non-alphabetical character, then exits the for loop and ask the user to enter again according to the condition.
                    Character.isLetter(char_name) : Returns 'false' if char_name has non-alphabetical characters. So in that case the condition should be active to prompt the error message for the user, if condition : false the if block won't be executed. SOLUTION : !Character.isLetter(char_name)
                 */
                if (!Character.isLetter(char_name) || name.contains(" "))
                {
                    System.out.println("\t\t▶ Name can't contain non-alphabetical characters.");
                    valid_name = false;
                    break;
                }

                //  Else which means no value is to be found as non-alphabetical , then validation passed.
                else
                {
                    user_details[0] = name;
                    valid_name = true;
                }
            }
        }

        //   Validates surname
        while (!valid_surname)
        {
            System.out.print("\tEnter your Surname  : ");
            surname = input.nextLine();

            if (surname.isEmpty())
            {
                System.out.println("\t\t▶ Surname has to be entered.");
                continue;
            }

            for (int i = 0; i < surname.length(); i++)
            {
                char char_surname = surname.charAt(i);

                if (!Character.isLetter(char_surname) || surname.contains(" "))
                {
                    System.out.println("\t\t▶ Surname can't contain non-alphabetical characters.");
                    valid_surname = false;
                    break;
                }
                else
                {
                    user_details[1] = surname;
                    valid_surname = true;
                }
            }
        }

        //  Validates email
        while (!valid_email)
        {
            System.out.print("\tEnter Your Email    : ");
            email = input.nextLine();

            if (email.isEmpty())
            {
                System.out.println("\t\t▶ Email has to be entered.");
                continue;
            }


            for (int i = 0; i < email.length(); i++)
            {
                //  By using the inbuilt contain() method checking the email for @ and . characters for validation.
                if (!email.contains("@") || !email.contains("mail."))
                {
                    System.out.println("\t\t▶ Invalid email. Email should contain '@' , 'mail.' ");
                    valid_email = false;
                    break;
                }
                else
                {
                    user_details[2] = email;
                    valid_email = true;
                }
            }
        }
        return user_details;
    }

    // ====================================================================================================================================== //



}