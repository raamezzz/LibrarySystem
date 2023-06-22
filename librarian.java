
import java.util.Comparator;

public class librarian extends user{
private String libraryID;
    public librarian(String username, String email, String password) {
        super(username, email, password);
        libraryID = "ST" + super.getLibraryID();
    }

    public void setLibraryID(String libraryID) {
        this.libraryID = libraryID;
    }

    public String getLibraryID() {
        return libraryID;
    }
    public void viewBooks(library lib){
        System.out.println(lib.getBooks());
    }
    public String viewLostBooks(library lib){
        return ""+ lib.getLostBooks();
    }
    public String viewClient(library lib, client user){
        if(lib.getClients().contains(user)){
            return "username: "+user.getUsername()
                    +"\nemail: " + user.getEmail()+
                    "\nlibrary ID: "+user.getLibraryID()+
                    "\nis late: "+ user.isLate()+
                    "\nborrowed books: "+user.getBorrowedBooks()+
                    "\nreturned books: "+user.getReturnedBooks()+
                    "\nlost books: "+user.getLostBooks();

        } else return "user not found";
    }
    public void sortBooksByTitle(library lib){

        lib.getBooks().sort(Comparator.comparing(book::getTitle));
    }
    public void sortBooksByISBN(library lib){

        lib.getBooks().sort(Comparator.comparing(book::getISBN));
    }
public client searchClient(library lib, String username){
    for(int i = 0; i < lib.getClients().size(); i++) {
        if (lib.getClients().get(i).getUsername().equals(username)) {
            System.out.println("user found");
            return lib.getClients().get(i);
        }

    }
    System.out.println("user not found");
    return null;
}
    public boolean deleteBook(library lib,book book ){
        if(lib.getBooks().contains(book)) {
            lib.getBooks().remove(book);
            System.out.println("book deleted successfully");
            return true;
        } else {
            System.out.println("book not found");
            return false;
        }
    }
    public boolean addBook(library lib, book book){
        if(lib.getBooks().contains(book)){
            System.out.println("book is already added");
            return false;
        }else {
            lib.getBooks().add(book);
            System.out.println("book added successfully");
            return true;
        }
    }
    public String toString(){
        return super.toString() + "\nlibrary ID: " + libraryID;
    }
}
