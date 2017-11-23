package com.bwie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.bwie.bean.GoodsBean;
import com.bwie.eventbus.MessageEvent;
import com.bwie.eventbus.PriceAndCountEvent;
import com.bwie.test.a09aproject.R;

import org.greenrobot.eventbus.EventBus;

import java.util.EventListener;
import java.util.List;

/**
 * Created by ASUS on 2017/11/22.
 */

public class GoodsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<GoodsBean.DataBean> groupList;
    private List<List<GoodsBean.DataBean.DatasBean>> childList;
    private final LayoutInflater inflater;

    public GoodsAdapter(Context context, List<GoodsBean.DataBean> groupList, List<List<GoodsBean.DataBean.DatasBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View convertView, ViewGroup viewGroup) {
        View view;
        final GroupViewHolder gholder;
        if (convertView==null){
            gholder=new GroupViewHolder();
            view=View.inflate(context,R.layout.item_cart_parent,null);
            gholder.check_group=view.findViewById(R.id.cb_parent);
            gholder.tv_number=view.findViewById(R.id.tv_number);
            view.setTag(gholder);
        }else {
            view=convertView;
            gholder= (GroupViewHolder) view.getTag();

        }

        final GoodsBean.DataBean dataBean = groupList.get(i);
        gholder.check_group.setChecked(dataBean.isChecked());
        gholder.tv_number.setText(dataBean.getTitle());
        //一级checkbox
        gholder.check_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBean.setChecked(gholder.check_group.isChecked());
                changeChildCbState(i,gholder.check_group.isChecked());
                EventBus.getDefault().post(compute());
                changeAllCbState(isAllGroupCbSelected());
                notifyDataSetChanged();
            }
        });


        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, final View convertView, ViewGroup viewGroup) {

        View view;
        final ChildViewHolder cholder;
        if (convertView==null){
            cholder=new ChildViewHolder();
            view=View.inflate(context,R.layout.item_cart_child,null);
            cholder.check_child=view.findViewById(R.id.cb_child);
            cholder.tv_tel=view.findViewById(R.id.tv_tel);
            cholder.tv_content = view.findViewById(R.id.tv_content);
            cholder.tv_time = view.findViewById(R.id.tv_time);
            cholder.tv_pri = view.findViewById(R.id.tv_pri);
            cholder.tv_del = view.findViewById(R.id.tv_del);
            cholder.iv_add = view.findViewById(R.id.iv_add);
            cholder.iv_del = view.findViewById(R.id.iv_del);
            cholder.tv_num = view.findViewById(R.id.tv_num);
            view.setTag(cholder);
        }else {
            view=convertView;
            cholder= (ChildViewHolder) view.getTag();

        }

        final GoodsBean.DataBean.DatasBean datasBean = childList.get(i).get(i1);
        cholder.check_child.setChecked(datasBean.isChecked());
        cholder.tv_tel.setText(datasBean.getType_name());
        cholder.tv_content.setText(datasBean.getMsg());
        cholder.tv_time.setText(datasBean.getAdd_time());
        cholder.tv_pri.setText(datasBean.getPrice()+"");
        cholder.tv_num.setText(datasBean.getNum()+"");
        cholder.check_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置该条目对象里的checked属性值
                datasBean.setChecked(cholder.check_child.isChecked());
                PriceAndCountEvent priceAndCountEvent=compute();
                EventBus.getDefault().post(priceAndCountEvent);
                if (cholder.check_child.isChecked()){
                    //当前checkbox是选中状态
                    if (isAllChildCbSelected(i)){
                        changeChildCbState(i,true);
                        changeAllCbState(isAllGroupCbSelected());
                    }
                }else {
                    changeChildCbState(i,false);
                    changeAllCbState(isAllGroupCbSelected());
                }
                notifyDataSetChanged();
            }
        });
        //加号
        cholder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=datasBean.getNum();
                cholder.tv_num.setText(++num+"");
                datasBean.setNum(num);
                if (cholder.check_child.isChecked()){
                    PriceAndCountEvent priceAndCountEvent=compute();
                    EventBus.getDefault().post(priceAndCountEvent);
                }
            }
        });
        //减号
        cholder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=datasBean.getNum();
                if (num==1){
                    return;
                }
                cholder.tv_num.setText(--num+"");
                datasBean.setNum(num);
                if (cholder.check_child.isChecked()){
                    PriceAndCountEvent priceAndCountEvent=compute();
                    EventBus.getDefault().post(priceAndCountEvent);
                }
            }
        });
        //删除
        cholder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<GoodsBean.DataBean.DatasBean> datasBeen = childList.get(i);
                GoodsBean.DataBean.DatasBean remove = datasBeen.remove(i1);
                if (datasBeen.size()==0){
                    childList.remove(i);
                    groupList.remove(i);
                }
                EventBus.getDefault().post(compute());
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class GroupViewHolder{
        CheckBox check_group;
        TextView tv_number;
    }

    class ChildViewHolder{
        CheckBox check_child;
        TextView tv_tel;
        TextView tv_content;
        TextView tv_time;
        TextView tv_pri;
        TextView tv_del;
        ImageView iv_del;
        ImageView iv_add;
        TextView tv_num;

    }

    /**
     * 改变全选的状态
     *
     * @param flag
     */

    private void changeAllCbState(boolean flag) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setChecked(flag);
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * 改变一级列表checkbox状态
     *
     * @param groupPosition
     */
    private void changGroupCbState(int groupPosition, boolean flag) {
        GoodsBean.DataBean dataBean = groupList.get(groupPosition);
        dataBean.setChecked(flag);
    }

    /**
     * 改变二级列表checkbox状态
     *
     * @param groupPosition
     * @param flag
     */

    private void changeChildCbState(int groupPosition,boolean flag){
        List<GoodsBean.DataBean.DatasBean> datasBeen = childList.get(groupPosition);
        for (int i = 0; i <datasBeen.size() ; i++) {
            GoodsBean.DataBean.DatasBean datasBean = datasBeen.get(i);
            datasBean.setChecked(flag);
        }
    }

    /**
     * 判断一级列表是否全部选中
     *
     * @return
     */
    private boolean isAllGroupCbSelected(){
        for (int i = 0; i <groupList.size() ; i++) {
            GoodsBean.DataBean dataBean = groupList.get(i);
            if (!dataBean.isChecked()) {
                return false;
            }
        }
          return true;
    }


    /**
     * 判断二级列表是否全部选中
     *
     * @param groupPosition
     * @return
     */
    private boolean isAllChildCbSelected(int groupPosition) {
        List<GoodsBean.DataBean.DatasBean> datasBeen = childList.get(groupPosition);
        for (int i = 0; i < datasBeen.size(); i++) {
            GoodsBean.DataBean.DatasBean datasBean = datasBeen.get(i);
            if (!datasBean.isChecked()) {
                return false;
            }
        }
        return true;
    }


    /**
     * 计算列表中，选中的钱和数量
     */
    private PriceAndCountEvent compute() {
        int count = 0;
        int price = 0;
        for (int i = 0; i < childList.size(); i++) {
            List<GoodsBean.DataBean.DatasBean> datasBeen = childList.get(i);
            for (int j = 0; j < datasBeen.size(); j++) {
                GoodsBean.DataBean.DatasBean datasBean = datasBeen.get(j);
                if (datasBean.isChecked()) {
                    price += datasBean.getNum() * datasBean.getPrice();
                    count += datasBean.getNum();
                }
            }
        }
        PriceAndCountEvent priceAndCountEvent = new PriceAndCountEvent();
        priceAndCountEvent.setCount(count);
        priceAndCountEvent.setPrice(price);
        return priceAndCountEvent;
    }
    /**
     * 设置全选、反选
     *
     * @param flag
     */
    public void changeAllListCbState(boolean flag) {
        for (int i = 0; i < groupList.size(); i++) {
            changGroupCbState(i, flag);
            changeChildCbState(i, flag);
        }
        EventBus.getDefault().post(compute());
        notifyDataSetChanged();
    }


}
