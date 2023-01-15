package Model;
public interface SelectListener {
    void onItemClicked(BookDiary model);
    void onRemoveClicked (BookDiary model) throws InterruptedException;
}
