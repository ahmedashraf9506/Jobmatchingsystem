package com.example.ahmed.jms;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
// page adapter
public class Pageradapter extends FragmentStatePagerAdapter {

    int numtab;
    public Pageradapter( FragmentManager fm,int numtab) {
        super(fm);
        this.numtab=numtab;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentCompany tab1 = new FragmentCompany();
                return tab1;
            case 1:
                FragmentJobs tab2 = new FragmentJobs();
                return tab2;
            case 2:
                FragmentAvailibile tab3 = new FragmentAvailibile();
                return tab3;
              default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return numtab;
    }
}
