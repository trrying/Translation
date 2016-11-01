package com.owm.biubiuboom.view.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.Toast;

import com.owm.biubiuboom.event.IEvent;

import butterknife.ButterKnife;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ouweiming on 2016/10/31.
 */

public class BaseDialogFragment extends AppCompatDialogFragment {
    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    protected Activity mActivity;

    public final PublishSubject<IEvent> getEventBus() {
        Activity activity = getActivity();
        if (BaseActivity.class.isInstance(activity)) {
            return ((BaseActivity) activity).getEventBus();
        }
        return PublishSubject.create();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        ButterKnife.bind(this, dialog);
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
    }

    private Toast mToast;
    public void showToast(@StringRes int resId) {
        showToast(getActivity().getText(resId));
    }
    public void showToast(CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

}
