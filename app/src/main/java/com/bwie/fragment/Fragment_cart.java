package com.bwie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwie.adapter.GoodsAdapter;
import com.bwie.bean.GoodsBean;
import com.bwie.eventbus.MessageEvent;
import com.bwie.eventbus.PriceAndCountEvent;
import com.bwie.presenter.GoodsPresenter;
import com.bwie.test.a09aproject.R;
import com.bwie.view.IView.IGoodsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by ASUS on 2017/11/9.
 */

public class Fragment_cart extends Fragment implements IGoodsActivity{
    private View view;
    private ExpandableListView mElv;
    private CheckBox mCheck_quan;
    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private GoodsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, null);
        EventBus.getDefault().register(this);
        initView(view);
        new GoodsPresenter(this).getGoods();
        mCheck_quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.changeAllListCbState(mCheck_quan.isChecked());
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView(View view) {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCheck_quan = (CheckBox) view.findViewById(R.id.check_quan);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
    }

    @Override
    public void showList(List<GoodsBean.DataBean> groupList, List<List<GoodsBean.DataBean.DatasBean>> childList) {
        adapter=new GoodsAdapter(getActivity(),groupList,childList);
        mElv.setAdapter(adapter);
        //取消二级列表的小箭头
        mElv.setGroupIndicator(null);
        //默认让其全部展开
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheck_quan.setChecked(event.isChecked());
    }
    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvNum.setText("结算(" + event.getCount() + ")");
        mTvPrice.setText(event.getPrice() + "");
    }

}
