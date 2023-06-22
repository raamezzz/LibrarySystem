
import java.util.ArrayList;
import java.util.Scanner;

public class library {
    private static final long serialVersionUID = 1L;
    private ArrayList<book> books;
    private ArrayList<client> clients = new ArrayList<client>();
    private ArrayList<librarian> librarians = new ArrayList<librarian>();
    private ArrayList<book> lostBooks;

    public library() {
    }

    public ArrayList<book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<book> books) {
        this.books = books;
    }

    public ArrayList<client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<client> clients) {
        this.clients = clients;
    }

    public boolean addBook(book a){
        if(books.contains(a)) {
            System.out.println("books is already added");
        return false;
        } else {
            books.add(a);
            System.out.println("book added successfully");
            return true;
        }
    }
    public boolean deleteBook(book a) {
        if (books.contains(a)) {
            books.remove(a);
            System.out.println("book removed successfully");
            return true;
        } else {
            System.out.println("book not found");
            return false;
        }
    }

    public ArrayList<book> getLostBooks() {
        return lostBooks;
    }

    public void setLostBooks(ArrayList<book> lostBooks) {
        this.lostBooks = lostBooks;
    }

    @Override
    public String toString() {
        return "library[" +
                "\nbooks: " + books +
                "\nclients: " + clients +
                "\nlost Books: " + lostBooks +
                ']';
    }

    public ArrayList<librarian> getLibrarians() {
        return librarians;
    }

    public void setLibrarians(ArrayList<librarian> librarians) {
        this.librarians = librarians;
    }
    public boolean newClient(client user){
if(clients.contains(user)){
    System.out.println("user already exists, try logging in instead");
    return false;
} else {
    clients.add(user);
    return true;
}
    }
    public boolean newLibrarian(librarian user){
        if(librarians.contains(user)){
            System.out.println("user already exists, try logging in instead");
            return false;
        } else {
            librarians.add(user);
            return true;
        }
    }
    public boolean logIn(user user){
        Scanner input = new Scanner(System.in);
           System.out.println(user.Display());
           return true;
    }
    public String displayBook(book a){
        return "" + a;
    }
}

