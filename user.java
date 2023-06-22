
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;

public class user {
    private String username;
    private String email;
    private String password;

    private final ArrayList<Integer> IDs = new ArrayList<>();
    private final String libraryID;

    public user(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        libraryID = "" + checkSimilarity();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public String getLibraryID(){
        return libraryID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public book searchBookByISBN(@NotNull library lib, String ISBN){
        for(int i = 0; i < lib.getBooks().size(); i++) {
            if (lib.getBooks().get(i).getISBN().equals(ISBN)) {
                System.out.println("book found");
                return lib.getBooks().get(i);
            }
            }
        System.out.println("not found");
        return null;
    }
    public book searchBookByTitle(@NotNull library lib, String title){
        for(int i = 0; i < lib.getBooks().size(); i++) {
            if (lib.getBooks().get(i).getTitle().toLowerCase().equals(title)) {
                System.out.println("book found");
                return lib.getBooks().get(i);
            }
        }
        System.out.println("not found");
        return null;
    }

    public String Display(){
        return "\nuser: " +
                "\nusername: " + username +
                "\nemail: " + email;
    }
    public String toString() {
        return "\nuser: " +
                "\nusername: " + username +
                "\nemail: " + email +
                "\npassword: " + password ;
    }
    public int checkSimilarity(){
        int num = (int)(Math.random() * 4000);
      if(!IDs.contains(num)){
          return num;
      }else {
          checkSimilarity();
      }
      return 0;
    }
}
