package com.owm.translation.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.owm.translation.R;

import java.util.List;

/**
 * 有道翻译返回适配器
 * Created by ouweiming on 2016/11/10.
 */

public class YoudaoResultExplainsAdapter extends BaseAdapter<String>{

    public YoudaoResultExplainsAdapter(List<String> data) {
        super(R.layout.item_youdao_result_explains, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_explain, s);
    }
}
