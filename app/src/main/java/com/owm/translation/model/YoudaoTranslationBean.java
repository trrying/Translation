package com.owm.translation.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有道翻译接口实体类
 * Created by ouweiming on 2016/11/9.
 */

public class YoudaoTranslationBean {


    /**
     * us_phonetic : ɡʊd
     * phonetic : gʊd
     * uk_phonetic : gʊd
     * explains : ["n. 好处；善行；慷慨的行为","adj. 好的；优良的；愉快的；虔诚的","adv. 好","n. (Good)人名；(英)古德；(瑞典)戈德"]
     */

    private BasicBean basic;
    /**
     * translation : ["好"]
     * basic : {"us_phonetic":"ɡʊd","phonetic":"gʊd","uk_phonetic":"gʊd","explains":["n. 好处；善行；慷慨的行为","adj. 好的；优良的；愉快的；虔诚的","adv. 好","n. (Good)人名；(英)古德；(瑞典)戈德"]}
     * query : good
     * errorCode : 0
     * web : [{"value":["好","善","商品"],"key":"Good"},{"value":["公共物品","公益事业","公共财"],"key":"public good"},{"value":["굿 닥터","Good Doctor (TV series)","好医生"],"key":"Good Doctor"}]
     */

    private String query;
    private String errorCode;
    private List<String> translation;
    /**
     * value : ["好","善","商品"]
     * key : Good
     */

    private List<WebBean> web;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public static class BasicBean {
        @SerializedName("us-phonetic")
        private String us_phonetic;
        private String phonetic;
        @SerializedName("uk-phonetic")
        private String uk_phonetic;
        private List<String> explains;

        public String getUs_phonetic() {
            return us_phonetic;
        }

        public void setUs_phonetic(String us_phonetic) {
            this.us_phonetic = us_phonetic;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUk_phonetic() {
            return uk_phonetic;
        }

        public void setUk_phonetic(String uk_phonetic) {
            this.uk_phonetic = uk_phonetic;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class WebBean {
        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }
/*
    版本：1.1，请求方式：get，编码方式：utf-8
    主要功能：中英互译，同时获得有道翻译结果和有道词典结果（可能没有）
    参数说明：
            　type - 返回结果的类型，固定为data
    　doctype - 返回结果的数据格式，xml或json或jsonp
    　version - 版本，当前最新版本为1.1
            　q - 要翻译的文本，必须是UTF-8编码，字符长度不能超过200个字符，需要进行urlencode编码
    　only - 可选参数，dict表示只获取词典数据，translate表示只获取翻译数据，默认为都获取
    　注： 词典结果只支持中英互译，翻译结果支持英日韩法俄西到中文的翻译以及中文到英语的翻译
    errorCode：
       0 - 正常
    　20 - 要翻译的文本过长
    　30 - 无法进行有效的翻译
    　40 - 不支持的语言类型
    　50 - 无效的key
    　60 - 无词典结果，仅在获取词典结果生效*/


    public final static String SUCCESS_CODE = "0";

    private final static Map<String, String> ERROR_CODES = new HashMap<>();

    static {
        ERROR_CODES.put("0","正常");
        ERROR_CODES.put("20","要翻译的文本过长");
        ERROR_CODES.put("30","无法进行有效的翻译");
        ERROR_CODES.put("40","不支持的语言类型");
        ERROR_CODES.put("50","无效的key");
        ERROR_CODES.put("60","无词典结果，仅在获取词典结果生效");
    }

    public static Map<String, String> getErrorCodes(){
        return ERROR_CODES;
    }

}
