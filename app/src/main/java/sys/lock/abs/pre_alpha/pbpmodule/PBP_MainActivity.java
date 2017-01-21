package sys.lock.abs.pre_alpha.pbpmodule;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

public class PBP_MainActivity extends AppCompatActivity implements PinFragment.onConfirmPinListener, PositionFragment.onConfirmPositionListener
{
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private NonSwipeableViewPager mNonSwipeableViewPager;
    private int pin = -1, position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pbp_container_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mNonSwipeableViewPager = (NonSwipeableViewPager) findViewById(R.id.container);
        mNonSwipeableViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void commitPin(int p)
    {
        this.pin = p;
        jumpToPosition();
    }

    @Override
    public void commitPosition(int pos)
    {
        this.position = pos;
        jumpToLockScreen();
    }

    public static class PlaceholderFragment extends Fragment
    {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment()
        {

        }

        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {

        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    PinFragment pinFragment = new PinFragment();
                    return pinFragment;

                case 1:
                    PositionFragment  positionFragment = new PositionFragment();
                    return positionFragment;

                case 2:
                    LockFragment  lockFragment = new LockFragment();
                    return lockFragment;
            }

            return null;
        }

        @Override
        public int getCount()
        {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch(position)
            {
                case 0:
                    return "Choose pin";

                case 1:
                    return "Place pin";

                case 2 :
                    return "Unlock it";
            }

            return null;
        }
    }

    public void jumpToPosition()
    {
        if(pin >= 0 && pin <= 99)
        {
            PositionFragment  positionFragment = (PositionFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            positionFragment.setPin(pin);

            mNonSwipeableViewPager.setCurrentItem(mNonSwipeableViewPager.getCurrentItem() + 1, true);
        }

        else
        {
            Toast.makeText(PBP_MainActivity.this, "Choose PIN to continue", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void jumpToLockScreen()
    {
        LockFragment lockFragment = (LockFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        lockFragment.setParams(pin, position);

        mNonSwipeableViewPager.setCurrentItem(mNonSwipeableViewPager.getCurrentItem() + 1, true);
    }
}
