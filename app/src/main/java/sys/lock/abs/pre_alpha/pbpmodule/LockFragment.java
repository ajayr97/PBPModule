package sys.lock.abs.pre_alpha.pbpmodule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LockFragment extends Fragment
{

    int pin = -1, pos[] = new int[2], temp = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.lock_fragment, container, false);
        final Button button2 = (Button) rootView.findViewById(R.id.button);

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pos[1] = temp % 10;
                pos[0] = temp / 10;
                int tens = 0, ones = 0, temp = 0, i = 0;

                Toast.makeText(getActivity(), " " + pin + " " + pos[0] + " " + pos[1], Toast.LENGTH_SHORT).show();

                while (i < 2)
                {
                    switch (pos[i])
                    {
                        case 1:
                        {
                            EditText et1 = (EditText) rootView.findViewById(R.id.editText1);
                            temp = Integer.parseInt(et1.getText().toString());
                            break;
                        }

                        case 2:
                        {
                            EditText et2 = (EditText) rootView.findViewById(R.id.editText2);
                            temp = Integer.parseInt(et2.getText().toString());
                            break;
                        }

                        case 3:
                        {
                            EditText et3 = (EditText) rootView.findViewById(R.id.editText3);
                            temp = Integer.parseInt(et3.getText().toString());
                            break;
                        }

                        case 4:
                        {
                            EditText et4 = (EditText) rootView.findViewById(R.id.editText4);
                            temp = Integer.parseInt(et4.getText().toString());
                            break;
                        }

                        case 5:
                        {
                            EditText et5 = (EditText) rootView.findViewById(R.id.editText5);
                            temp = Integer.parseInt(et5.getText().toString());
                            break;
                        }

                        case 6:
                        {
                            EditText et6 = (EditText) rootView.findViewById(R.id.editText6);
                            temp = Integer.parseInt(et6.getText().toString());
                            break;
                        }
                    }

                    if (i == 0)
                        tens = temp * 10;
                    if (i == 1)
                        ones = temp;
                    i++;

                }

                final int pinEntered = tens + ones;

                if (pinEntered == pin)
                    Toast.makeText(getActivity(), "Success Final", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Failure final "+ pinEntered + " "
                            + pin, Toast
                            .LENGTH_SHORT).show();
            }

        });

        return rootView;
    }

    public void setParams(int p, int pos)
    {
        this.pin = p;
        this.temp = pos;
    }
}