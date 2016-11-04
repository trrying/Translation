package com.owm.translation.model;

import com.owm.biubiuboom.model.BaseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度翻译接口实体类
 * Created by ouweiming on 2016/11/2.
 */

public class BaiduTranslationBean extends BaseBean{


    /**
     * from : en
     * to : zh
     * trans_result : [{"src":"apple","dst":"苹果"}]
     */

    private String from;
    private String to;
    /**
     * src : apple
     * dst : 苹果
     */

    private List<TransResultBean> trans_result;
    /**
     * error_code : 58001
     * error_msg : INVALID_TO_PARAM
     * monLang : zh
     * query : ocean
     */

    private String error_code;
    private String error_msg;
    private String monLang;
    private String query;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransResultBean> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransResultBean> trans_result) {
        this.trans_result = trans_result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getMonLang() {
        return monLang;
    }

    public void setMonLang(String monLang) {
        this.monLang = monLang;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public static class TransResultBean {
        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }
//-------------------------------------------- 访问返回错误码 star
//    错误码	含义	解决方法
//    52000	成功
//    52001	请求超时	重试
//    52002	系统错误	重试
//    52003	未授权用户	检查您的appid是否正确
//    54000	必填参数为空	检查是否少传参数
//    58000	客户端IP非法	检查您填写的IP地址是否正确 可修改您填写的服务器IP地址
//    54001	签名错误	请检查您的签名生成方法
//    54003	访问频率受限	请降低您的调用频率
//    58001	译文语言方向不支持	检查译文语言是否在语言列表里
//    54004	账户余额不足	前往管理控制台为账户充值
//    54005	长query请求频繁	请降低长query的发送频率，3s后再试

    public final static String successCode = "52000";

    private final static Map<String, String> ERROR_CODES = new HashMap<>();

    static {
        ERROR_CODES.put("52000","成功");
        ERROR_CODES.put("52001","请求超时	重试");
        ERROR_CODES.put("52002","系统错误	重试");
        ERROR_CODES.put("52003","未授权用户	检查您的appid是否正确");
        ERROR_CODES.put("54000","必填参数为空	检查是否少传参数");
        ERROR_CODES.put("58000","客户端IP非法	检查您填写的IP地址是否正确 可修改您填写的服务器IP地址");
        ERROR_CODES.put("54001","签名错误	请检查您的签名生成方法");
        ERROR_CODES.put("54003","访问频率受限	请降低您的调用频率");
        ERROR_CODES.put("58001","译文语言方向不支持	检查译文语言是否在语言列表里");
        ERROR_CODES.put("54004","账户余额不足	前往管理控制台为账户充值");
        ERROR_CODES.put("54005","长query请求频繁	请降低长query的发送频率，3s后再试");
    }

    public static Map<String, String> getErrorCodes() {
        return ERROR_CODES;
    }
//-------------------------------------------- 访问返回错误码 end


//-------------------------------------------- 语言类型 star
//    语言列表
//    源语言语种不确定时可设置为 auto，目标语言语种不可设置为 auto。
//    语言简写	名称
//    auto	自动检测
//    zh	中文
//    en	英语
//    yue	粤语
//    wyw	文言文
//    jp	日语
//    kor	韩语
//    fra	法语
//    spa	西班牙语
//    th	泰语
//    ara	阿拉伯语
//    ru	俄语
//    pt	葡萄牙语
//    de	德语
//    it	意大利语
//    el	希腊语
//    nl	荷兰语
//    pl	波兰语
//    bul	保加利亚语
//    est	爱沙尼亚语
//    dan	丹麦语
//    fin	芬兰语
//    cs	捷克语
//    rom	罗马尼亚语
//    slo	斯洛文尼亚语
//    swe	瑞典语
//    hu	匈牙利语
//    cht	繁体中文

    private final static List<LanguageBaidu> LANGUAGE_BAIDU_LIST = new ArrayList<>();

    public static List<LanguageBaidu> getLanguageBaiduList() {
        return LANGUAGE_BAIDU_LIST;
    }

    static {
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("auto","自动检测"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("zh","中文"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("en","英语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("yue","粤语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("wyw","文言文"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("jp","日语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("kor","韩语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("fra","法语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("spa","西班牙语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("th","泰语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("ara","阿拉伯语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("ru","俄语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("pt","葡萄牙语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("de","德语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("it","意大利语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("el","希腊语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("nl","荷兰语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("pl","波兰语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("bul","保加利亚语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("est","爱沙尼亚语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("dan","丹麦语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("fin","芬兰语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("cs","捷克语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("rom","罗马尼亚语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("slo","斯洛文尼亚语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("swe","瑞典语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("hu","匈牙利语"));
        LANGUAGE_BAIDU_LIST.add(new LanguageBaidu("cht","繁体中文"));
    }

    public static class LanguageBaidu {
        public String LanguageShorthand;//语言简写
        public String languageName; //语言名称

        public LanguageBaidu(String languageShorthand, String languageName) {
            LanguageShorthand = languageShorthand;
            this.languageName = languageName;
        }
    }
//-------------------------------------------- 语言类型 end

}
