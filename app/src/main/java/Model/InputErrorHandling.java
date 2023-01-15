package Model;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

public class InputErrorHandling implements TextWatcher {
    EditText et;
    public InputErrorHandling(EditText et){
        this.et = et;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    @Override
    public void afterTextChanged(Editable editable) {
        String title = et.getText().toString();
        if(TextUtils.isEmpty(title)) {
            et.setError("Please enter data");
            return;
        }
    }

}

