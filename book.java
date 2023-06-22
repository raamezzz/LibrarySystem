import java.util.Date;

public class book {
    private String title, authorName, category, ISBN;
    private final int numberOfCopies;
    private int numberOfAvailableCopies;
    private Date dueDate;
    private final int publicationYear;
    private boolean isBorrowable;
   private final double price;

   private final double fine;

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public int getNumberOfAvailableCopies() {
        return numberOfAvailableCopies;
    }

    public void setNumberOfAvailableCopies(int numberOfAvailableCopies) {
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public book(String title, String authorName, String category, String ISBN, int numberOfCopies, int numberOfAvailableCopies,
                int publicationYear, boolean isBorrowable, double price)
    {
        this.title = title;
        this.authorName = authorName;
        this.category = category;
        this.ISBN = ISBN;
        this.numberOfCopies = numberOfCopies;
        this.numberOfAvailableCopies = numberOfAvailableCopies;
        this.publicationYear = publicationYear;
        this.isBorrowable = isBorrowable;
        this.price = price;
        fine = price / 8;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getISBN() {
        return  ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isBorrowable() {
        return isBorrowable;
    }

    public void setBorrowable(boolean borrowable) {
        isBorrowable = borrowable;
    }

    public double getPrice() {
        return price;
    }

  @Override
  public String toString() {
    return "\ntitle: " + title +
            "\nauthor Name: " + authorName+
            "\ncategory: " + category +
            "\nISBN: " + ISBN +
            "\nnumber Of Copies: " + numberOfCopies +
            "\nnumber Of Available Copies: " + numberOfAvailableCopies +
            "\npublication Year: " + publicationYear +
            "\nis Borrowable: " + isBorrowable +
            "\nprice: " + price +
            "\ndue Date: " + dueDate;
  }

  public String DisplayBook() {
        return "\ntitle: " + title +
                "\nauthor Name: " + authorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getFine() {
        return fine;
    }
}