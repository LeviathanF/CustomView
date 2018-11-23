package com.example.msi_.customview.viewholder;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.msi_.customview.R;
import com.example.msi_.customview.bean.RVBean;


/**
 * @Author：ZC
 * @Time： 2018/7/19 10:36
 * @Description：只有一个TextView的ViewHolder
 **/
public class SingleButtonHolder extends BaseViewHolder<RVBean> {

    private TextView tvContent;

    @SuppressWarnings("unused")
    public static BaseViewHolder.ViewHolderCreator HOLDER_CREATOR = new ViewHolderCreator(){
        @Override
        public SingleButtonHolder createByViewGroupAndType(ViewGroup group, int type) {
            return new SingleButtonHolder(group);
        }
    };

    private SingleButtonHolder(ViewGroup root) {
        super(root.getContext(), root, R.layout.vh_button);
    }

    @Override
    protected void initView() {
        tvContent = itemView.findViewById(R.id.tv_content);
    }

    @Override
    public void bindData(final RVBean bean, int position) {
        tvContent.setText(bean.getName());
        final Class activity = bean.getActivity();
        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),activity);
                intent.putExtra("mode",bean.getMode());
                getContext().startActivity(intent);
            }
        });
    }
}
