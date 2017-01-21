package sys.lock.abs.pre_alpha.pbpmodule;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class PinFragment extends Fragment
{
    EditText et;

    public interface onConfirmPinListener
    {
        public void commitPin(int p);
    }

    int pin = -1;

    private NonSwipeableViewPager mViewPager;
    onConfirmPinListener confirmPinListener;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            confirmPinListener = (onConfirmPinListener) activity;
        }

        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.pin_fragment, container, false);
        et = (EditText) rootView.findViewById(R.id.editText1);
        final Button b2 = (Button) rootView.findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                et.setEnabled(false);
                pin = Integer.parseInt(et.getText().toString());
                confirmPinListener.commitPin(pin);
            }
        });

        return rootView;
    }

    private int getItem(int i)
    {
        return mViewPager.getCurrentItem() + i;
    }


}