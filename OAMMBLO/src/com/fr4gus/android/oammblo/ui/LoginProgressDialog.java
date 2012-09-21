package com.fr4gus.android.oammblo.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class LoginProgressDialog extends DialogFragment {

	public static DialogFragment newInstance() {
		return new LoginProgressDialog();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		ProgressDialog waitdialog = new ProgressDialog(getActivity());
		;
		waitdialog.setMessage("Autenticando\u2026");
		return waitdialog;
	}

}
