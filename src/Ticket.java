import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket
{
    private char RowLetter;
    private int SeatNumber;
    private double SeatPrice;
    private Person Person;

    //  Declaration of Ticket Constructor. Object in buy_seat method of PLaneManagement class.
    public Ticket( char row_letter , int seat_number , double seat_price , Person person_information)
    {
        this.RowLetter = row_letter;
        this.SeatNumber = seat_number;
        this.SeatPrice = seat_price;
        this.Person = person_information;
    }

    //  Declaration of the Ticket constructor that saves rowLetter and seatNumber in Ticket class. Object in cancel_seat method of PlaneManagement class
    public Ticket ( char rowLetter , int seatNumber )
    {
        this.RowLetter = rowLetter;
        this.SeatNumber = seatNumber;
    }

    //  Declaration of TicketInformation Method which contains Ticket Details , Person Information and prints out when invoked.
    public void TicketInformation ()
    {
        System.out.println("\n\t|-------------- TICKET DETAILS --------------|");
        System.out.println("\t ■ ROW     =   " + RowLetter);
        System.out.println("\t ■ SEAT    =   " + SeatNumber);
        System.out.println("\t ■ PRICE   =   $" + SeatPrice);
        System.out.println("\t|--------------------------------------------|");
        Person.PersonInformation();
    }


    // Declaration of a method to save and write Ticket Information and User Details into a File when called upon.
    public void SaveFile()
    {
        String FileName = "" + RowLetter + "" + SeatNumber + ".txt";

        try
                
        {
            FileWriter file_writer = new FileWriter(FileName);

            file_writer.write("TICKET INFORMATION\n");
            file_writer.write("\tROW     : " + RowLetter + "\n");
            file_writer.write("\tSEAT    : " + SeatNumber + "\n");
            file_writer.write("\tPRICE   : " + SeatPrice + "\n");
            file_writer.write("\nPERSON DETAILS\n");
            file_writer.write("\tNAME    : " + Person.getName() + "\n");
            file_writer.write("\tSURNAME : " + Person.getSurname() + "\n");
            file_writer.write("\tEMAIL   : " + Person.getEmail() + "\n");
            file_writer.close();
        }
        catch (IOException e)
        {
            System.out.println("ERROR OCCURRED : " + e);
        }
    }

    // Declaration of a method that deletes written Ticket Information and User Details off the file and the file itself when invoked..
    public void DeleteFile()
    {
        String FileName = "" + RowLetter + "" + SeatNumber + ".txt";

        File file = new File(FileName);

        if (file.exists())
        {
            if (file.delete())
            {
                System.out.println(FileName + " FILE : SUCCESSFULLY DELETED");
            }
            else
            {
                System.out.println(FileName + " FILE : DELETE UNSUCCESSFUL");
            }
        }
        else
        {
            System.out.println(FileName + " FILE DO NOT EXISTS");
        }
    }


    //  Declaration of Setters
    public void setRowLetter(char row_letter)
    {
        RowLetter = row_letter;
    }
    public void setSeatNumber(int seat_number)
    {
        SeatNumber = seat_number;
    }
    public void setSeatPrice(double seat_price)
    {
        SeatPrice = seat_price;
    }
    public void setPerson(Person person_information)
    {
        Person = person_information ;
    }

    //  Declaration of Getters
    public char getRowLetter()
    {
        return RowLetter;
    }
    public int getSeatNumber()
    {
        return SeatNumber;
    }
    public double getSeatPrice()
    {
        return SeatPrice;
    }
    public Person getPerson()
    {
        return Person;
    }

}
