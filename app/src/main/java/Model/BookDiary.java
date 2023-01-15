package Model;

import java.util.Random;

public class BookDiary {
    private String titleName, pagesRead, bookComments, parentName, dateRead;
    private int id;
    public BookDiary( String titleName, String pagesRead, String bookComments, String parentName, String dateRead) {
        this.id = gen();
        this.titleName = titleName;
        this.pagesRead = pagesRead;
        this.bookComments = bookComments;
        this.parentName = parentName;
        this.dateRead = dateRead;
    }
    public int getId(){
       return this.id;
    }
    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(String pagesRead) {
        this.pagesRead = pagesRead;
    }

    public String getBookComments() {
        return bookComments;
    }

    public void setBookComments(String bookComments) {
        this.bookComments = bookComments;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDateRead() {
        return dateRead;
    }

    public void setDateRead(String dateRead) {
        this.dateRead = dateRead;
    }
    public String printBookDiary(){
        return this.titleName + " " + this.pagesRead + " " + this.bookComments + " " + this.parentName + " " + this.dateRead;
    }
    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        return 10000 + r.nextInt(20000);
    }


}
