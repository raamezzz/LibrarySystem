import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class client extends user{
    private boolean isLate;
    private double fine;
    private static final int maximumNumOfBooks = 50;
    private String libraryID;

  public void setLibraryID(String libraryID) {
    this.libraryID = libraryID;
  }

  private ArrayList<book> borrowedBooks = new ArrayList<>();
    private ArrayList<book> returnedBooks = new ArrayList<>();
    private ArrayList<book> lostBooks = new ArrayList<>();

    public boolean borrow(book a){
      if(borrowedBooks.size() < maximumNumOfBooks && a.isBorrowable() && !borrowedBooks.contains(a)){
        borrowedBooks.add(a);
        a.setNumberOfAvailableCopies(a.getNumberOfAvailableCopies() - 1);
        if (a.getNumberOfAvailableCopies() < 2) a.setBorrowable(false);
        Date due = new Date();
        due.setDate(due.getDate() + 14);
        a.setDueDate(due);
        return true;
      }
      else if(borrowedBooks.size() >= maximumNumOfBooks ){
        System.out.println("maximum number of books reached, please return a book first");
        return false;
      } else if(borrowedBooks.contains(a)){
        System.out.println("book is already borrowed");
        return false;
      }
      return false;
    }
    public boolean returnBook(book a){
      Scanner input = new Scanner(System.in);
        if(borrowedBooks.contains(a)) {
          if(a.getDueDate().after(new Date())){
            borrowedBooks.remove(a);
            a.setNumberOfAvailableCopies(a.getNumberOfAvailableCopies()+1);
            returnedBooks.add(a);
            System.out.println("book returned successfully");
            return true;
          }else {
            fine += a.getFine();
            borrowedBooks.remove(a);
            a.setNumberOfAvailableCopies(a.getNumberOfAvailableCopies()+1);
            returnedBooks.add(a);
            System.out.println("you have an outstanding fine of "+fine+'$');
            System.out.print("to pay the fine press 0, to exit press 1");
            byte ans = input.nextByte();
            switch (ans){
              case 0 -> {
                System.out.println("enter the amount of money you want to pay: ");
                double money = input.nextDouble();
                payFine(money);
              }
              case 1 ->System.out.println("thank you for using our services!");
              default -> System.out.println("wrong input, please try again later");
            }
          }
        }
        else {
          return false;
        }
        return false;
    }

    public client(String username, String email, String password) {
        super(username, email, password);
               libraryID = "SD" + super.getLibraryID();
    }

public String toStringSimple(){
      return "username: " + getUsername()+ "\nemail: " + getEmail() + "\nlibrary ID: " + libraryID;
}
    public String toString() {
        return super.toString()+ "\nclient[" +
                "\nis Late: " + isLate +
                "\nfine: " + fine +
                "\nlibrary ID: " + libraryID +
                "\nborrowed Books: " + borrowedBooks +
                "\nreturned Books: " + returnedBooks +
                "\nlost Books: " + lostBooks +
                "] ";
    }

    public boolean payFine(double money){
        System.out.println("your outstanding fine is: " + fine);
        if (fine > money){
            System.out.println("not enough money");
            return false;
        }else if(fine < money){
            fine = 0;
            System.out.println("fine has been paid successfully");
            return true;
        }else return false;

    }

  public boolean isLate() {
    return isLate;
  }

  public void setLate(boolean late) {
    isLate = late;
  }

  public double getFine() {
    return fine;
  }

  public void setFine(double fine) {
    this.fine = fine;
  }

  @Override
  public String getLibraryID() {
    return libraryID;
  }

  public ArrayList<book> getBorrowedBooks() {
    return borrowedBooks;
  }

  public void setBorrowedBooks(ArrayList<book> borrowedBooks) {
    this.borrowedBooks = borrowedBooks;
  }

  public ArrayList<book> getReturnedBooks() {
    return returnedBooks;
  }

  public void setReturnedBooks(ArrayList<book> returnedBooks) {
    this.returnedBooks = returnedBooks;
  }

  public ArrayList<book> getLostBooks() {
    return lostBooks;
  }

  public void setLostBooks(ArrayList<book> lostBooks) {
    this.lostBooks = lostBooks;
  }
}
