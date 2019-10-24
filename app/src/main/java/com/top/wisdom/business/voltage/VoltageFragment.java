package com.top.wisdom.business.voltage;

import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.top.wisdom.R;
import com.top.wisdom.base.BaseMvpFragment;
import com.top.wisdom.business.testFragment.testFragment;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class VoltageFragment extends BaseMvpFragment<VoltagePresenter> implements VoltageContract.View {

    private static final String TAG ="VoltageFragment";


    @BindView(R.id.vp_voltage)
    ViewPager vpVoltage;
    @BindView(R.id.tab_voltage)
    TabLayout tabVoltage;

    private FmPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"导杆压降","卡具压降","换极压降"};

/*    public static VoltageFragment newInstance() {
        Bundle args = new Bundle();
        VoltageFragment fragment = new VoltageFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    protected void initView(View view) {
        Log.e(TAG,"-------------init-------------");
        parentActivity.setToolbarTitle("压降数据查询");

        parentActivity.setBack(true);
        parentActivity.closeDrawerLayout();
        parentActivity.getToolbar().setNavigationOnClickListener(v -> {
            parentActivity.onBackPressed();
            //Toast.makeText(parentActivity, "返回了", Toast.LENGTH_SHORT).show();
        });

        //vpVoltage.setOffscreenPageLimit();

        fragments.clear();

        for(int i=0;i<titles.length;i++){
            fragments.add(new testFragment());
            tabVoltage.addTab(tabVoltage.newTab());
        }


        tabVoltage.setupWithViewPager(vpVoltage,false);
        pagerAdapter = new FmPagerAdapter(fragments,getChildFragmentManager());

        //pagerAdapter = new FmPagerAdapter(fragments,parentActivity.getSupportFragmentManager());
        vpVoltage.setAdapter(pagerAdapter);


        for(int i=0;i<titles.length;i++){
            tabVoltage.getTabAt(i).setText(titles[i]);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        parentActivity.unLockDrawerLayout();
        parentActivity.setBack(false);
        parentActivity.getToolbar().setNavigationOnClickListener(null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_voltage;
    }

    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return null;
    }


     class FmPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public FmPagerAdapter(List<Fragment> fragmentList, FragmentManager fm) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public int getCount() {
            return fragmentList != null && !fragmentList.isEmpty() ? fragmentList.size() : 0;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
