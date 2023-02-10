package com.danielme.textinputlayout;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

  private TextInputEditText editTextEmail;
  private TextInputEditText editTextPassword;
  private TextInputLayout textInputEmail;
  private TextInputLayout textInputPassword;
  private TextInputLayout textInputHelp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setupToolbar();

    bindViews();
  }

  private void bindViews() {
    editTextEmail = findViewById(R.id.editTextEmail);
    editTextPassword = findViewById(R.id.editTextPassword);
    textInputEmail = findViewById(R.id.text_input_layout_email);
    textInputPassword = findViewById(R.id.text_input_layout_pass);
    ((TextInputLayout) findViewById(R.id.text_input_layout_help)).setEndIconOnClickListener(v -> {
      Toast.makeText(this, R.string.help_message, Toast.LENGTH_LONG).show();
    });

  }

  private void setupToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  public void validate(View view) {
    String mailError = null;
    if (TextUtils.isEmpty(editTextEmail.getText())) {
      mailError = getString(R.string.mandatory);
    }
    toggleTextInputLayoutError(textInputEmail, mailError);

    String passError = null;
    if (TextUtils.isEmpty(editTextPassword.getText())) {
      passError = getString(R.string.mandatory);
    }
    toggleTextInputLayoutError(textInputPassword, passError);

    clearFocus();
  }

  /**
   * Display/hides TextInputLayout error.
   *
   * @param msg the message, or null to hide
   */
  private static void toggleTextInputLayoutError(@NonNull TextInputLayout textInputLayout,
                                                 String msg) {
    textInputLayout.setError(msg);
    textInputLayout.setErrorEnabled(msg != null);
  }

  private void clearFocus() {
    View view = this.getCurrentFocus();
    if (view instanceof EditText) {
      InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context
              .INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
      view.clearFocus();
    }
  }

}
