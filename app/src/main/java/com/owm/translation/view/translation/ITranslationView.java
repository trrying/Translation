package com.owm.translation.view.translation;

import com.owm.biubiuboom.view.mvp.IView;
import com.owm.translation.model.BaiduTranslationBean;
import com.owm.translation.model.YoudaoTranslationBean;

/**
 * MainView 接口
 * Created by ouweiming on 2016/10/31.
 */

public interface ITranslationView extends IView {

    void showResult(BaiduTranslationBean model);

    void showResult(YoudaoTranslationBean model);

}
