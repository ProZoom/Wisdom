package com.top.wisdom.business.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.top.wisdom.R;
import com.top.wisdom.base.BaseMvpFragment;
import com.top.wisdom.bean.GridBean;
import com.top.wisdom.business.testFragment.testFragment;
import com.top.wisdom.business.voltage.VoltageFragment;
import com.top.wisdom.intf.onItemClickListenner;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class MainFragment extends BaseMvpFragment<MainPresenter> implements MainContract.View {

    private static final String TAG ="MainFragment";

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    private     MainAdapter adapter;

    private List<GridBean> mDatas = new ArrayList<>();
    private String[] titleDats;
    private int[] iconDatas;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        Log.e(TAG,"-------------init-------------");
        parentActivity.toggleDrawer();

        parentActivity.setToolbarTitle(getResources().getString(R.string.company_name));

        //parentActivity.unLockDrawerLayout();
        //mPresenter.updateMainAdapterData(getActivity(),adapter);
        initMainAdapter();
    }



    private void initMainAdapter() {
        mDatas.clear();
        titleDats = new String[]{
                "测温",
                "测压",
                "测振",
                "测距",
                "测燥"
        };
        iconDatas = new int[]{
                R.drawable.wisdom,
                R.drawable.wisdom,
                R.drawable.wisdom,
                R.drawable.wisdom,
                R.drawable.wisdom
        };

        for (int i = 0; i < titleDats.length; i++) {
            mDatas.add(new GridBean(iconDatas[i], titleDats[i]));
        }
        adapter = new MainAdapter(getActivity(), mDatas);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        rvMain.setLayoutManager(manager);
        rvMain.setAdapter(adapter);

        adapter.setOnItemClickListenner(new onItemClickListenner() {
            @Override
            public void onLongClickListenner(int position) {}
            @Override
            public void onClickListenner(int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
                openFragment(position);
            }
        });
    }


    private void openFragment(int position) {
        MainActivity parentActivity = (MainActivity) getActivity();
        Fragment fragment=new Fragment();
        switch (position) {
            case 0:
                fragment= new VoltageFragment();
                break;
            case 1:
                fragment= testFragment.newInstance();
                break;
            case 2:
                fragment= testFragment.newInstance();
                break;
            case 3:
                fragment= testFragment.newInstance();
                break;
            case 4:
                fragment= testFragment.newInstance();
                break;
        }


        parentActivity.toFragmentBackStack(fragment);

        parentActivity.commitFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return null;
    }
}
