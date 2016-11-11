package com.owm.translation.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 默认adapter，使用BaseViewHolder来绑定数据
 * Created by ouweiming on 2016/11/11.
 */

public abstract class BaseAdapter<M> extends BaseQuickAdapter<M, BaseViewHolder> {
    public BaseAdapter(int layoutResId, List<M> data) {
        super(layoutResId, data);
    }

    public BaseAdapter(List<M> data) {
        super(data);
    }
}
