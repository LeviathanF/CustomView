package com.example.msi_.customview.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Author：ZC
 * @Time： 2018/7/17 11:23
 * @Description：
 **/
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(Context context, ViewGroup root, int layoutRes) {
        super(LayoutInflater.from(context).inflate(layoutRes, root, false));
        initView();
    }

    @SuppressWarnings("unused")
    public Context getContext(){
        if (itemView.getContext()!=null){
            return itemView.getContext();
        }
        return null;
    }

    protected abstract void initView();
    public void bindActivityListener(View.OnClickListener listener){}

    /**
     * 用给定的 data 对 holder 的 view 进行赋值
     */
    public abstract void bindData(T t,int position);

    /**
     * 判断是否被选中
     */
    public void setSelected(boolean flag){}

    public interface ViewHolderCreator<VH extends BaseViewHolder>{
        VH createByViewGroupAndType(ViewGroup group, int type);
    }
}