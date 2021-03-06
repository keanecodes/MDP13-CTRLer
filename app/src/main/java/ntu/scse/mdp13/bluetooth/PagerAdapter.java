package ntu.scse.mdp13.bluetooth;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ntu.scse.mdp13.R;


public class PagerAdapter extends FragmentPagerAdapter {

    public enum TabItem {
        CONNECTION(BluetoothFragment.class, R.string.tab_connection),
        MESSAGE(MessageFragment.class, R.string.tab_message);

        private final Class<? extends Fragment> fragmentClass;
        private final int titleResId;

        TabItem(Class<? extends Fragment> fragmentClass, @StringRes int titleResId) {
            this.fragmentClass = fragmentClass;
            this.titleResId = titleResId;
        }
    }

    private final TabItem[] tabItems;
    private final Context context;

    public PagerAdapter(FragmentManager fm, Context context, TabItem... tabItems) {
        super(fm);
        this.tabItems = tabItems;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) { return newInstance(tabItems[position].fragmentClass); }

    private Fragment newInstance(Class<? extends Fragment> fragmentClass) {
        try {
            return fragmentClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("fragment must have public no-arg constructor: " + fragmentClass.getName(), e);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(tabItems[position].titleResId);
    }

    @Override
    public int getCount() { return tabItems.length; }
}
