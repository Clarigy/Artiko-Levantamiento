package com.example.artikolevyinf.Fragments.Adapters;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSection.LowVoltageSection1ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSection.LowVoltageSection2ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSection.LowVoltageSection3ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSection.LowVoltageSection4ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSection.LowVoltageSection5ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport.LowVoltageSupport1ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport.LowVoltageSupport2ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport.LowVoltageSupport3ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport.LowVoltageSupport4ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport.LowVoltageSupport5ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport.LowVoltageSupport6ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport.LowVoltageSupport7ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer.Transformer1ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer.Transformer2ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer.Transformer3ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer.Transformer4ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer.Transformer5ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer.Transformer6ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer.Transformer7ActivityFragment;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.Transformer.Transformer8ActivityFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int totalPages;
    int activityPage;

    public ViewPagerAdapter(FragmentManager fm, int numPages, int actualPage) {
        super(fm);
        this.totalPages = numPages;
        this.activityPage = actualPage;
    }

    @Override
    public Fragment getItem(int position) {
        //De acuerdo a la posición de la lista, se envia un fragment u otro
        Fragment fragment = null;

        switch (activityPage) {
            case 1:
                ArrayList<Fragment> arrayPagesListTransformer = new ArrayList<Fragment>() {{ add(new Transformer1ActivityFragment()); add(new Transformer2ActivityFragment()); add(new Transformer3ActivityFragment()); add(new Transformer4ActivityFragment()); add(new Transformer5ActivityFragment()); add(new Transformer6ActivityFragment()); add(new Transformer7ActivityFragment()); add(new Transformer8ActivityFragment());}};
                fragment = arrayPagesListTransformer.get(position);
                break;
            case 2:
                ArrayList<Fragment> arrayPagesListCenter = new ArrayList<Fragment>() {{ add(new Transformer1ActivityFragment()); }};
                fragment = arrayPagesListCenter.get(position);
                break;
            case 3:
                ArrayList<Fragment> arrayPagesListLowVoltageSupport = new ArrayList<Fragment>() {{ add(new LowVoltageSupport1ActivityFragment()); add(new LowVoltageSupport2ActivityFragment()); add(new LowVoltageSupport3ActivityFragment()); add(new LowVoltageSupport4ActivityFragment()); add(new LowVoltageSupport5ActivityFragment()); add(new LowVoltageSupport6ActivityFragment()); add(new LowVoltageSupport7ActivityFragment()); }};
                fragment = arrayPagesListLowVoltageSupport.get(position);
                break;
            case 4:
                ArrayList<Fragment> arrayPagesListUnderground = new ArrayList<Fragment>() {{ add(new Transformer1ActivityFragment()); add(new Transformer1ActivityFragment()); }};
                fragment = arrayPagesListUnderground.get(position);
                break;
            case 5:
                ArrayList<Fragment> arrayPagesListLowVoltageSection = new ArrayList<Fragment>() {{ add(new LowVoltageSection1ActivityFragment()); add(new LowVoltageSection2ActivityFragment()); add(new LowVoltageSection3ActivityFragment()); add(new LowVoltageSection4ActivityFragment()); add(new LowVoltageSection5ActivityFragment()); }};
                fragment = arrayPagesListLowVoltageSection.get(position);
                break;
        }

        assert fragment != null;
        return fragment;
    }


    @Override
    public int getCount() {
        // Show total number of pages.
        int TOTAL_PAGES = totalPages;
        return TOTAL_PAGES;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return  "1";

            case 1:
                return "2";

            default:
                return  "3";

        }
    }

}