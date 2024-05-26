public class Person
{

    //  Declaration of instance variables.
    private String Name;
    private String Surname;
    private String Email;

    // Declaration of a constructor that accepts user_name, user_surname, user_email from the PlaneManagement class and initialize the corresponding attributes of a Person object.
    public Person ( String user_name , String user_surname , String user_email )
    {
        this.Name = user_name;
        this.Surname = user_surname;
        this.Email = user_email;
    }

    public void PersonInformation ()
    {
        System.out.println("\n\t|------------- CUSTOMER DETAILS -------------|");
        System.out.println("\t ■ CUSTOMER NAME    = " + Name);
        System.out.println("\t ■ CUSTOMER SURNAME = " + Surname);
        System.out.println("\t ■ CUSTOMER EMAIL   = " + Email);
        System.out.println("\t|--------------------------------------------|\n");
    }

    //  Using setters : For the data in PlaneManagement class to be assigned into the private properties in Person class.
    //  In this case NO USE of setters, Bcz Data is being assigned using constructor.
    public void setName(String name)
    {
        this.Name = name;
    }
    public void setSurname(String surname)
    {
        this.Surname = surname;
    }
    public void setEmail(String email)
    {
        this.Email = email;
    }


    //  Using getters : For the methods which consists of Private properties to be accessed outside the class.
    //  In this case NO USE of getters, Bcz private properties are being printed out using Method.
    public String getName()
    {
        return Name;
    }
    public String getSurname()
    {
        return Surname;
    }
    public String getEmail()
    {
        return Email;
    }









}
