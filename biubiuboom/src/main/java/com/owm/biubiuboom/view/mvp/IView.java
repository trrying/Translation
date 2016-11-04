package com.owm.biubiuboom.view.mvp;

/**
 * 默认view接口
 * Created by ouweiming on 2016/10/31.
 */

public interface IView {
    /**  显示缓冲条  */
    void showLoading();
    /**  隐藏缓冲条  */
    void hideLoading();
    /*显示错误信息*/
    void showMessageDialog(String message);
}
