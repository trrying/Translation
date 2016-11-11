package com.owm.translation.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.owm.translation.R;
import com.owm.translation.model.YoudaoTranslationBean;

import java.util.List;

/**
 * 有道翻译-网络释义adapter
 * Created by ouweiming on 2016/11/10.
 */

public class YoudaoResultWebAdapter extends BaseAdapter<YoudaoTranslationBean.WebBean> {

    public YoudaoResultWebAdapter(List<YoudaoTranslationBean.WebBean> data) {
        super(R.layout.item_youdao_result_web, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YoudaoTranslationBean.WebBean webBean) {
        baseViewHolder.setText(R.id.tv_key, webBean.getKey());
        if (webBean.getValue() != null && !webBean.getValue().isEmpty()) {
            StringBuilder values = new StringBuilder();
            for (String value : webBean.getValue()) {
                values.append(value).append("；");
            }
            baseViewHolder.setText(R.id.tv_value, values.toString());
        }
    }
}
