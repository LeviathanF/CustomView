package com.example.msi_.customview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.msi_.customview.viewholder.BaseViewHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author：ZC
 * @Time： 2018/7/17 11:22
 * @Description：
 **/
public class OnlyOneTypeAdapter<D> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<D> list = new ArrayList<>();
    private HashMap<String, BaseViewHolder.ViewHolderCreator> creatorHashMap = new HashMap<>();
    private Class<?> vhClass;
    private View.OnClickListener listener;
    private int selectedPosition = -1;

    public OnlyOneTypeAdapter(Class<?> vhClass) {
        this.vhClass = vhClass;
    }

    public void setList(List<D> list) {
        try {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("initContent", "initContent: " + e.getMessage());
        }
    }

    public void setListener(View.OnClickListener listener) {
        try {
            this.listener = listener;
        } catch (Exception e) {
            Log.e("initContent", "initContent: " + e.getMessage());
        }
    }

    public void addData(D data) {
        this.list.add(data);
    }

    @Override
    public BaseViewHolder<D> onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            BaseViewHolder.ViewHolderCreator creator = null;
            if (creatorHashMap.containsKey(vhClass.getName())) {
                creator = creatorHashMap.get(vhClass.getName());
            } else {
                try {
                    creator = (BaseViewHolder.ViewHolderCreator) vhClass.getField("HOLDER_CREATOR").get(null);
                    creatorHashMap.put(vhClass.getName(), creator);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (creator != null) {
                return creator.createByViewGroupAndType(parent, viewType);
            }
        } catch (Exception e) {
            Log.e("initContent", "initContent: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        try {
            holder.bindData(list.get(position), position);
            if (selectedPosition != -1) {
                if (selectedPosition == position) {
                    holder.setSelected(true);
                } else {
                    holder.setSelected(false);
                }
            }
            if (listener != null) {
                holder.bindActivityListener(listener);
            }
        } catch (Exception e) {
            Log.e("initContent", "initContent: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        try {
            return list.size();
        } catch (Exception e) {
            Log.e("initContent", "initContent: " + e.getMessage());
        }
        return 0;
    }

    /**
     * 设置被选中的item
     */
    public void setSelectedPosition(int selectedPosition) {
        try {
            this.selectedPosition = selectedPosition;
        } catch (Exception e) {
            Log.e("initContent", "initContent: " + e.getMessage());
        }
    }

    /**
     * 移除指定的项目
     */
    public void removeItem(int position){
        if (position>list.size()){
            return;
        }
        list.remove(position);
        notifyItemRemoved(position);
        if (position != list.size()){
            notifyItemRangeChanged(position,list.size()-position);
        }
    }

    /**
     * 添加指定项目
     * @param position 添加位置
     * @param content 添加内容
     */
    public void addItem(int position,D content){
        list.add(position,content);
        notifyItemInserted(position);
        if (position != list.size()){
            notifyItemRangeChanged(position,list.size()-position);
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
