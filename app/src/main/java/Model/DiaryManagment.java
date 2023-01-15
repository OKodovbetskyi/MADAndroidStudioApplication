package Model;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;

//This class contains functionality related to managing Book Entries
public class DiaryManagment {
    //This is storage of all Book Diaries
    private static List<BookDiary> diaries = new ArrayList<BookDiary>();
    public static void addDiaryEntry(BookDiary bookentry){
        diaries.add(bookentry);
    }
    //Method that prints all books as a String
    public static String printBooks(){
        String diariesPrinted ="";
        for (BookDiary book: diaries){
            diariesPrinted += book.printBookDiary() + System.lineSeparator();
        }
        return diariesPrinted;
    }
    //Method that get single book by provided ID.
    public static BookDiary getBookDiaryById(int id){
        for (BookDiary book: diaries){
            if (book.getId() == id){
                return book;
            }
        }
        return null;
    }
    public static List<BookDiary> getListOfBooks (){
        return diaries;
    }
    //Method that removes book from List
    public static void removeDiaryEntry(BookDiary diary){
        diaries.remove(diary);
        System.out.println(diaries);
    }
    //Search books by its values
    public static List<BookDiary> findBookIfAnyCharacterMatch(String ch){
        List<BookDiary> newList = new ArrayList<BookDiary>();
        String searching = ch.toLowerCase();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            diaries.forEach((diary -> {
                if (diary.getTitleName().toLowerCase().equals(searching)
                        || diary.getBookComments().toLowerCase().equals(searching)
                        || diary.getPagesRead().equals(searching)
                        || diary.getDateRead().equals(searching)
                        || diary.getParentName().toLowerCase().equals(searching)){
                    newList.add(diary);
                }
            }));
            if (newList.size() == 0){
                newList.add(new BookDiary("I couldnt find anything","","","",""));
            }
        }

        return newList;
    }
}
