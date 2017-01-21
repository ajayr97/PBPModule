package sys.lock.abs.pre_alpha.pbpmodule;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class PositionFragment extends Fragment
{
    TextView tv3, tv4;
    public int position1 = 1, position2 = 1, pin = -1;
    int mixed = 0;
    NumberPicker np1, np2;

    public interface onConfirmPositionListener
    {
        public void commitPosition(int pos);
    }

    onConfirmPositionListener confirmPositionListener;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            confirmPositionListener = (onConfirmPositionListener) activity;
        }

        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.position_fragment, container, false);

        tv3 = (TextView) rootView.findViewById(R.id.textView2);
        tv4 = (TextView) rootView.findViewById(R.id.textView5);

        final TextView tv1 = (TextView) rootView.findViewById(R.id.textView14);
        np1 = (NumberPicker)rootView.findViewById(R.id.numberPicker1);
        tv1.setTextColor(Color.parseColor("#ffd32b3b"));

        np1.setMinValue(1);
        np1.setMaxValue(6);
        np1.setWrapSelectorWheel(true);

        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                tv1.setText("Selected Number : " + newVal);
                position1=newVal;

                if (position1 == position2)
                    Toast.makeText(getActivity(),"please select different positions ", Toast.LENGTH_SHORT).show();
            }
        });

        final TextView tv2 = (TextView) rootView.findViewById(R.id.textView15);
        np2 = (NumberPicker)rootView.findViewById(R.id.numberPicker2);
        tv2.setTextColor(Color.parseColor("#ffd32b3b"));

        np2.setMinValue(1);
        np2.setMaxValue(6);
        np2.setWrapSelectorWheel(true);

        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                tv2.setText("Selected Number : " + newVal);
                position2=newVal;
            }
        });

        final Button button1 = (Button) rootView.findViewById(R.id.button);

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(position1 != position2)
                {
                    mixed = (position1 * 10) + position2;
                    confirmPositionListener.commitPosition(mixed);
                    np2.setEnabled(false);
                    np1.setEnabled(false);
                }

                else
                {
                    Toast.makeText(getActivity(),"Select different positions ", Toast
                            .LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    public void setPin(int p)
    {
        this.pin = p;
        CharSequence a = "Choose position for units digit [" + String.valueOf(pin%10) + "] of your pin :";
        tv3.setText(a);
        CharSequence b = "Choose position for tens digit [" + String.valueOf(pin/10) + "] of  your pin :";
        tv4.setText(b);
    }
}
