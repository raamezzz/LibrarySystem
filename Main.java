

import java.io.*;
import java.util.*;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


public class Main {
    public static void main(String[] args) {
      try {
        File books = new File("books.txt");
        File clients = new File("clients.txt");
        File librarians = new File("librarians.txt");
        library bib = new library();

        bib.setBooks(readBooks(books));
        bib.setClients(readClients(clients));
        bib.setLibrarians(readLibrarian(librarians));
        addBooks(bib);
        clearFile(books);
        clearFile(clients);
        clearFile(librarians);


        startMenu(bib);

        FileWriter boo = new FileWriter(books);
        boo.write(""+bib.getBooks());
        FileWriter cl = new FileWriter(clients);
        cl.write(""+bib.getClients());
        FileWriter li = new FileWriter(librarians);
        li.write(""+bib.getLibrarians());
        cl.close();
        li.close();
        boo.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  public static void addBooks(library lib){
   ArrayList<book> a = new ArrayList<>();
    a.add(new book("A Monster Calls","Patrick Ness", "novel / fiction", "978-0-7636-5633-1",5, 5, 2011,true,8.49));
    a.add(new book("The Perks Of Being a Wallflower","Stephen Chbosky", "novel / fiction", "978-0671027346",10, 10,1999,true,11.55));
    a.add(new book("The Maid","Nita Prose", "thriller / fiction", "9780593356159",10, 10,2022,true,14.4));
    a.add(new book("Tomorrow, and Tomorrow, and Tomorrow", "Gabrielle Zevin", "novel", "9780593321201", 15,  15,2022, true, 15.49  ));
    a.add(new book("Rich Dad, Poor Dad", "Robert T.Kiyosaki", "self-help", "9780751532715", 15,  15,2001, true, 16.13 ));
    a.add(new book("Babel: An Arcane History", "R.F.Kuang", "fantasy / fiction", "9780063021426", 7, 7, 2022, true, 14.99 ));
    a.add(new book("The White Album", "Joan Didion", "memoir / nonfiction", "9780374532079", 5,  5,1979, true, 12.59));
    a.add(new book("All the Light We Cannot See", "Anthony Doerr", "historical / fiction","9781476746586", 7, 7,2014,true, 13.74 ));
    a.add(new book("What Happened To You?: Conversations on Trauma, Resilience, and Healing", "Bruce D. Perry, Oprah Winfrey", "nonfiction / self-help", "9781250223180", 10,  10, 2021, true,15.99 ));
    a.add(new book("We Are the Ants", "Shaun David Hutchinson", "novel / fiction", "9781481449632", 10,  10,2016, true, 11.99 ));
    a.add(new book("The Picture of Dorian Gray", "Oscar Wilde, Jeffrey Eugenides","novel / fiction" , "9781785996177", 5,  5,1890, true, 9));
    lib.setBooks(a);
  }
  public static void clearFile(File file){
    try {
      Scanner s = new Scanner(file);
      Formatter f = new Formatter(file);
      while(s.hasNext()){
        f.format(" ");
    }
      } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  public static void addUsers(library lib){
     ArrayList<client> u;
    u = new ArrayList<>();
    u.add(new client("Bob","bob@builder.com","iLoveMyHammer53"));
     u.add(new client("Aang", "aang.avatar@Ba-sing-se.net","momo3232"));
     u.add(new client("Ramez", "ramez.mina.2023@aiu.edu.eg", "niceTry"));
     lib.setClients(u);
  }
  public static void startMenu(library lib){
      Scanner input = new Scanner(System.in);
    System.out.println("welcome! for logging in: press 0, for signing up: press 1, to exit: press 2");
    try {
      byte ans = input.nextByte();
      switch(ans){
        case 0 -> {
          System.out.println("for clients press 0, for staff press 1: ");
          byte ans1 = input.nextByte();
          System.out.println("username: ");
          String username = input.next();
          System.out.println("password: ");
          String password = input.next();
          switch (ans1){
            case 0-> {
              for(int i = 0; i<lib.getClients().size(); i++){
                if(lib.getClients().get(i).getUsername().equals(username)){
                  if(lib.getClients().get(i).getPassword().equals(password)){
                    clientMenu(lib,lib.getClients().get(i));
                    break;
                  }else{
                    System.out.println("wrong password, please try again");
                    startMenu(lib);
                  }
                  break;
                }
              }
              System.out.println("user not found");
              startMenu(lib);
            }
            case 1 -> {
              for(int i = 0; i<lib.getLibrarians().size(); i++){
                if(lib.getLibrarians().get(i).getUsername().equals(username)){
                  if(lib.getLibrarians().get(i).getPassword().equals(password)){
                    librarianMenu(lib,lib.getLibrarians().get(i));
                    break;
                  }else{
                    System.out.println("wrong password, please try again");
                    startMenu(lib);
                  }
                }else{
                  System.out.println("user not found");
                  startMenu(lib);
                }
              }
            }
            default -> {
              System.out.println("wrong input, please try again");
              startMenu(lib);
            }
          }
        }
        case 1 -> {
          System.out.println("for a new client press 0, for a new staff member press 1:");
          byte u = input.nextByte();
          switch (u){
            case 0 -> {
              lib.newClient(newClient());
              System.out.println("user added successfully!");
              startMenu(lib);
            }
            case 1 -> {
              lib.newLibrarian(newLibrarian());
              System.out.println("user added successfully!");
              startMenu(lib);
            }
            default -> {
              System.out.println("wrong input, please try again");
              startMenu(lib);
            }

          }
        }
        case 2 -> System.out.println("thank you for using our services");
        default -> {
          System.out.println("wrong input, please try again");
          startMenu(lib);
        }

      }
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("wrong input");
    }


  }
  public static void clientMenu(library lib,client client ){
      Scanner input = new Scanner(System.in);
    System.out.println("welcome "+client.getUsername()+"!");
    System.out.println("for search: press 0, to show borrowed books: press 1, to go back: press 2, to exit: press 3");
    try {
      byte ans = input.nextByte();
      switch (ans) {
        case 0 -> {
          System.out.println("to search by title: press 0, to search by ISBN: press 1, to exit: press 2");
          byte ans1 = input.nextByte();
          switch (ans1) {
            case 0 -> searchBook(lib, client);
            case 1 -> {
              System.out.println("enter the ISBN: ");
              String ISBN = input.next();
              System.out.println(client.searchBookByISBN(lib, ISBN));
              clientMenu(lib, client);
            }
            case 2 -> System.out.println("thank you for using our services!");
            default -> {
              System.out.println("wrong input, please try again");
              clientMenu(lib, client);
            }
          }
        }
        case 1 -> borrowedMenu(lib, client);
        case 2 -> startMenu(lib);
        case 3 -> System.out.println("thank you for using our services!");
        default -> {
          System.out.println("wrong input, please try again");
          clientMenu(lib, client);
        }
      }
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException();
    }
  }
  public static client newClient(){
      Scanner input = new Scanner(System.in);
    System.out.print("username: ");
    String username = input.nextLine();
    System.out.print("email address: ");
    String email = input.next();
    System.out.print("password: ");
    String password = input.next();

    return new client(username,email,password);
  }
  public static librarian newLibrarian(){
    Scanner input = new Scanner(System.in);
    System.out.print("username: ");
    String username = input.nextLine();
    System.out.print("email address: ");
    String email = input.next();
    System.out.print("password: ");
    String password = input.next();

    return new librarian(username,email,password);
  }
  public static void searchBook(library lib, user user) {

    Scanner tit = new Scanner(System.in);
    System.out.println("enter the title: ");
    String title = tit.nextLine();
    if (user instanceof client) {
      if (title.isEmpty()) {
        System.out.println("please enter a title");
      } else {
        if (user.searchBookByTitle(lib, title.toLowerCase()) == null) {
          clientMenu(lib, (client) user);
        }
        System.out.println(user.searchBookByTitle(lib, title.toLowerCase()));
        System.out.println("to borrow it: press 0, to search for another book: press 1, to exit: press 2 ");
        byte BorN = tit.nextByte();
        switch (BorN) {
          case 0 -> {
            ((client) user).borrow(user.searchBookByTitle(lib, title));
            clientMenu(lib, (client) user);
          }
          case 1 -> searchBook(lib, user);
          case 2 -> System.out.println("thank you for using our services!");
          default -> {
            System.out.println("wrong input, please try again");
            searchBook(lib, user);
          }
        }
      }
    } else if (user instanceof librarian) {
      if (title.isEmpty()) {
        System.out.println("please enter a title");
      } else {
        if (user.searchBookByTitle(lib, title.toLowerCase()) == null) {
          librarianMenu(lib,(librarian)user);
        } else {
          System.out.println(user.searchBookByTitle(lib, title.toLowerCase()));
          librarianMenu(lib, (librarian) user);
        }
      }
    }
  }
  public static void borrowedMenu(library lib, client client){
    System.out.println(client.getBorrowedBooks());
    System.out.println("to return a book: press 0, to go back: press 1, to exit: press 2");
    Scanner input = new Scanner(System.in);
    byte ans = input.nextByte();
    switch (ans){
      case 0 ->{
        System.out.println("enter title of the book: ");
        Scanner tit = new Scanner(System.in);
        String title = tit.nextLine();
        client.returnBook(client.searchBookByTitle(lib, title));
        clientMenu(lib, client);
      }
      case 1 -> clientMenu(lib,client);
      case 2 -> System.out.println("thank you for using our services!");
      default -> {
        System.out.println("wrong input, please try again");
        borrowedMenu(lib, client);
      }
    }
  }
public static void librarianMenu(library lib, librarian staff){
  System.out.println("hello "+staff.getUsername()+"!\nto search for a book: press 0\nto search for a client: press 1\nto update the library: press 2\nto view all books: press 3\nto go back: press 4\nto exit press: 5");
Scanner input = new Scanner(System.in);
byte ans = input.nextByte();
switch (ans){
  case 0 -> searchBook(lib, staff);
  case 1 -> {
    System.out.println("enter the username of the client: ");
    String client = input.next();
    System.out.println(staff.viewClient(lib,staff.searchClient(lib,client)));
    librarianMenu(lib, staff);
  }
  case 2 ->updateLibrary(lib, staff);
  case 3 ->{
    staff.viewBooks(lib);
    librarianMenu(lib,staff);
  }
  case 4 ->startMenu(lib);
  case 5 ->System.out.println("thank you for using our services!");
  default -> {
    System.out.println("wrong input, please try again");
    librarianMenu(lib, staff);
  }
}
    }
    public static book newBook(){
      Scanner input = new Scanner(System.in);
      System.out.println("title: ");
      String title = input.nextLine();
      System.out.println("author name: ");
      String author = input.nextLine();
      System.out.println("category: ");
      String category = input.nextLine();
      System.out.println("ISBN: ");
      String ISBN = input.next();
      System.out.println("number of copies: ");
      int noOfCopies = input.nextInt();
      System.out.println("number of available copies: ");
      int noOfAvailableCopies = input.nextInt();
      System.out.println("publication year: ");
      int publicationYear = input.nextInt();
      boolean isBorrowable = noOfCopies > 1;
      System.out.println("price: ");
      double price = input.nextDouble();
      return new book(title,author,category,ISBN,noOfCopies,noOfAvailableCopies,publicationYear,isBorrowable,price);
    }
    public static void updateLibrary(library lib, librarian staff){
      Scanner input = new Scanner(System.in);
      System.out.println("to sort books by title: press 0\nto sort books by ISBN: press 1\nto add a book: press 2\nto delete a book: press 3\nto go back: press 4\nto exit: press 5");
      byte a = input.nextByte();
      switch (a){
        case 0 ->{
          staff.sortBooksByTitle(lib);
          System.out.println(lib.getBooks());
          updateLibrary(lib,staff);

        }
        case 1 ->{
          staff.sortBooksByISBN(lib);
          System.out.println(lib.getBooks());
          updateLibrary(lib,staff);
        }
        case 2 -> {
          staff.addBook(lib,newBook());
          updateLibrary(lib,staff);
        }
        case 3 ->{
          Scanner tit = new Scanner(System.in);
          System.out.println("enter the title of the book");
          String title = tit.nextLine().toLowerCase();
          staff.deleteBook(lib, staff.searchBookByTitle(lib,title));
          updateLibrary(lib,staff);
        }
        case 4 -> librarianMenu(lib, staff);
        case 5 -> System.out.println("thank you for using our services!");
        default -> {
          System.out.println("wrong input, please try again.");
          updateLibrary(lib,staff);
        }
      }
    }
    public static ArrayList<book> readBooks(File file){
      ArrayList<book> books = new ArrayList<>();
      try {
        Scanner myReader = new Scanner(file);
        String title = "", author = "", category = "", ISBN = "";
        int noOfCopies = 0, noOfAvailableCopies = 0, pubYear = 0;
        boolean isBorrowable = true;
        double price;
        while(myReader.hasNextLine()){
          String Data = myReader.nextLine();
            if (Data.contains("title: ")) {
              String[] line = Data.split(": ");
              title = line[1];
            } else if (Data.contains("author Name: ")) {
              String[] line = Data.split(": ");
              author = line[1];
            } else if (Data.contains("category: ")) {
              String[] line = Data.split(": ");
              category = line[1];
            }else if (Data.contains("ISBN: ")){
              String[] line = Data.split(": ");
              ISBN = line[1];
            }else if (Data.contains("number Of Copies: ")) {
              String[] line = Data.split(": ");
              noOfCopies = parseInt(line[1]);
            }else if (Data.contains("number Of Available Copies:")) {
              String[] line = Data.split(": ");
              noOfAvailableCopies = parseInt(line[1]);
            }else if (Data.contains("publication Year:")) {
              String[] line = Data.split(": ");
              pubYear = parseInt(line[1]);
            }else if (Data.contains("is Borrowable:")) {
              String[] line = Data.split(": ");
              isBorrowable = parseBoolean(line[1]);
            }else if (Data.contains("price:")) {
              String[] line = Data.split(": ");
              price = parseDouble(line[1]);
              books.add(new book(title,author,category,ISBN,noOfCopies,noOfAvailableCopies,pubYear,isBorrowable,price));
            }

        }

      }catch (FileNotFoundException e){
        System.out.println("an error occurred");
        e.printStackTrace();
      }
      return books;
  }
  public static ArrayList<client> readClients(File file) {
    ArrayList<client> clients = new ArrayList<>();
    try {
      Scanner myReader = new Scanner(file);
      String title = "", author = "", category = "", ISBN = "";
      int noOfCopies = 0, noOfAvailableCopies = 0, pubYear = 0;
      boolean isBorrowable = true;
      double price = 0;
      Date dueDate = null;
      String username = "", password = "", email = "", libraryid = "";
      boolean isLate;
      double fine = 0;
      ArrayList<book> bBooks = new ArrayList<>(), rBooks = new ArrayList<>(), lostBooks = new ArrayList<>();
      book b = null;
      client r = null;
      while (myReader.hasNextLine()) {
        String Data = myReader.nextLine();
        if (Data.contains("username: ")) {
          String[] line = Data.split(": ");
          username = line[1];
        } else if (Data.contains("password: ")) {
          String[] line = Data.split(": ");
          password = line[1];
        } else if (Data.contains("email: ")) {
          String[] line = Data.split(": ");
          email = line[1];
        } else if (Data.contains("is Late: ")) {
          String[] line = Data.split(": ");
          isLate = parseBoolean(line[1]);
        } else if (Data.contains("fine: ")) {
          String[] line = Data.split(": ");
          fine = parseDouble(line[1]);
        } else if (Data.contains("library ID: ")) {
          String[] line = Data.split(": ");
          libraryid = line[1];
          r = new client(username, email, password);
        } else if (Data.equals("borrowed Books: [")) {

          while(!Data.contains("]")) {

            Data = myReader.nextLine();

            if (Data.contains("title: ")) {
              String[] line = Data.split(": ");
              title = line[1];
            } else if (Data.contains("author Name: ")) {
              String[] line = Data.split(": ");
              author = line[1];
            } else if (Data.contains("category: ")) {
              String[] line = Data.split(": ");
              category = line[1];
            } else if (Data.contains("ISBN: ")) {
              String[] line = Data.split(": ");
              ISBN = line[1];
            } else if (Data.contains("number Of Copies: ")) {
              String[] line = Data.split(": ");
              noOfCopies = parseInt(line[1]);
            } else if (Data.contains("number Of Available Copies:")) {
              String[] line = Data.split(": ");
              noOfAvailableCopies = parseInt(line[1]);
            } else if (Data.contains("publication Year:")) {
              String[] line = Data.split(": ");
              pubYear = parseInt(line[1]);
            } else if (Data.contains("is Borrowable:")) {
              String[] line = Data.split(": ");
              isBorrowable = parseBoolean(line[1]);
            } else if (Data.contains("price:")) {
              String[] line = Data.split(": ");
              price = parseDouble(line[1]);
            }else if (Data.contains("due Date:")){
              String[] line = Data.split(": ");
              if(!line[1].contains("null")){
              String[] Date = line[1].split(" ");
              int month = 1;
switch (Date[1]){
  case("Jan")->month=1;
  case "Feb" ->month=2;
  case "Mar" ->month=3;
  case "Apr" ->month=4;
  case "May" ->month=5;
  case "Jun" ->month=6;
  case "Jul" ->month=7;
  case "Aug" ->month=8;
  case "Sep" ->month=9;
  case "Oct" ->month=10;
  case "Nov" ->month=11;
  case "Dec" ->month=12;
}
              dueDate = new Date(parseInt(Date[5].substring(0,4)) - 1900,month-1 ,parseInt(Date[2]));
              }
              b = new book(title, author, category, ISBN, noOfCopies, noOfAvailableCopies, pubYear, isBorrowable, price);
              b.setDueDate(dueDate);
              r.getBorrowedBooks().add(b);
            }
          }

        } else if (Data.contains("returned Books")) {

          if(Data.equals("returned Books: [")){
            while(!Data.contains("]")) {

              Data = myReader.nextLine();

              if (Data.contains("title: ")) {
                String[] line = Data.split(": ");
                title = line[1];
              } else if (Data.contains("author Name: ")) {
                String[] line = Data.split(": ");
                author = line[1];
              } else if (Data.contains("category: ")) {
                String[] line = Data.split(": ");
                category = line[1];
              } else if (Data.contains("ISBN: ")) {
                String[] line = Data.split(": ");
                ISBN = line[1];
              } else if (Data.contains("number Of Copies: ")) {
                String[] line = Data.split(": ");
                noOfCopies = parseInt(line[1]);
              } else if (Data.contains("number Of Available Copies:")) {
                String[] line = Data.split(": ");
                noOfAvailableCopies = parseInt(line[1]);
              } else if (Data.contains("publication Year:")) {
                String[] line = Data.split(": ");
                pubYear = parseInt(line[1]);
              } else if (Data.contains("is Borrowable:")) {
                String[] line = Data.split(": ");
                isBorrowable = parseBoolean(line[1]);
              } else if (Data.contains("price:")) {
                String[] line = Data.split(": ");
                price = parseDouble(line[1]);
                b = new book(title, author, category, ISBN, noOfCopies, noOfAvailableCopies, pubYear, isBorrowable, price);
                r.getReturnedBooks().add(b);
              }
            }
          }
        } else if (Data.contains("lost Books")) {
          while(!Data.contains("]")) {

            Data = myReader.nextLine();

            if (Data.contains("title: ")) {
              String[] line = Data.split(": ");
              title = line[1];
            } else if (Data.contains("author Name: ")) {
              String[] line = Data.split(": ");
              author = line[1];
            } else if (Data.contains("category: ")) {
              String[] line = Data.split(": ");
              category = line[1];
            } else if (Data.contains("ISBN: ")) {
              String[] line = Data.split(": ");
              ISBN = line[1];
            } else if (Data.contains("number Of Copies: ")) {
              String[] line = Data.split(": ");
              noOfCopies = parseInt(line[1]);
            } else if (Data.contains("number Of Available Copies:")) {
              String[] line = Data.split(": ");
              noOfAvailableCopies = parseInt(line[1]);
            } else if (Data.contains("publication Year:")) {
              String[] line = Data.split(": ");
              pubYear = parseInt(line[1]);
            } else if (Data.contains("is Borrowable:")) {
              String[] line = Data.split(": ");
              isBorrowable = parseBoolean(line[1]);
            } else if (Data.contains("price:")) {
              String[] line = Data.split(": ");
              price = parseDouble(line[1]);
              b = new book(title, author, category, ISBN, noOfCopies, noOfAvailableCopies, pubYear, isBorrowable, price);
              r.getLostBooks().add(b);
            }
          }
          r.setFine(fine);
          r.setLibraryID(libraryid);
          clients.add(r);
        }
      }

    } catch (FileNotFoundException e) {
      System.out.println("error");
      e.printStackTrace();
    }
    return clients;
  }
  public static ArrayList<librarian> readLibrarian(File file) {
    ArrayList<librarian> librarians = new ArrayList<>();
    try {
      Scanner myReader = new Scanner(file);
      String username = "", password = "", email = "", libraryid;
      librarian r = null;
      while (myReader.hasNextLine()) {
        String Data = myReader.nextLine();
        if (Data.contains("username: ")) {
          String[] line = Data.split(": ");
          username = line[1];
        } else if (Data.contains("password: ")) {
          String[] line = Data.split(": ");
          password = line[1];
        } else if (Data.contains("email: ")) {
          String[] line = Data.split(": ");
          email = line[1];
        } else if (Data.contains("library ID: ")) {
          String[] line = Data.split(": ");
          libraryid = line[1];
          r = new librarian(username,email,password);
          r.setLibraryID(libraryid);
          librarians.add(r);
        }


      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
return librarians;
  }
    }