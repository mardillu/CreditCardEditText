package com.devmarvel.creditcardentrydemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.devmarvel.creditcardentry.library.CardType;
import com.devmarvel.creditcardentry.library.CardValidCallback;
import com.devmarvel.creditcardentry.library.CreditCard;
import com.devmarvel.creditcardentry.library.CreditCardForm;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	CardValidCallback cardValidCallback = new CardValidCallback() {
		@Override
		public void cardValid(CreditCard card) {
			Log.d(TAG, "valid card: " + card);
			Toast.makeText(MainActivity.this, "Card valid and complete", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void CvvStarted() {
			Log.d(TAG, "CvvStarted: ");
		}

		@Override
		public void CvvEnded() {
			Log.d(TAG, "CvvEnded: ");
		}

		@Override
		public void cardTypeChanged(CardType type) {
			Log.d(TAG, "cardTypeChanged: " + type.toString());
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final CreditCardForm noZipForm = findViewById(R.id.form_no_zip);
		noZipForm.setOnCardValidCallback(cardValidCallback);

		// we can track gaining or losing focus for any particular field.
		noZipForm.setOnFocusChangeListener((v, hasFocus) -> Log.d(TAG, v.getClass().getSimpleName() + " " + (hasFocus ? "gained" : "lost") + " focus. card valid: " + noZipForm.isCreditCardValid()));

		final CreditCardForm zipForm = findViewById(R.id.form_with_zip);
		zipForm.setOnCardValidCallback(cardValidCallback);
		final CreditCardForm yellowForm = findViewById(R.id.yellow_form);
		yellowForm.setOnCardValidCallback(cardValidCallback);
		final CreditCardForm justCard   = findViewById(R.id.just_card_form);
		justCard.setOnCardValidCallback(cardValidCallback);
		final CreditCardForm cardAndZip   = findViewById(R.id.card_and_zip_form);
		cardAndZip.setOnCardValidCallback(cardValidCallback);

		final CreditCardForm prepopulated = findViewById(R.id.pre_populated_form);
		prepopulated.setOnCardValidCallback(cardValidCallback);
		// populate the card, but don't try to focus the next field
		prepopulated.setCardNumber("4242 4242 4242 4242", false);

		final CreditCardForm clear = findViewById(R.id.clear_test_form);
		findViewById(R.id.clear_test_button).setOnClickListener(v -> clear.clearForm());
	}

}
