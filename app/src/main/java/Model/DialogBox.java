package Model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.coursworkmadandroidstudio.MainScreenActivity;

//Dialog box for alert
public class DialogBox extends AlertDialog.Builder {
    Context ct;
    private boolean result=true;
    public DialogBox(Context ct, String title, String message){
        super(ct);
        this.ct=ct;
        setTitle(title);
        setMessage(message);
        setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       setResult(true);
                        dialogInterface.cancel();
                    }
                });
        show();

    }
    public void setResult(Boolean res){
        this.result = res;
    }
    public Boolean getResult(){
        return this.result;
    }






}
