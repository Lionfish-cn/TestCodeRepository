package com.code.repository.spider;

import com.alibaba.fastjson.JSONObject;

import java.util.Properties;
import java.util.regex.Pattern;

public class SpliderRun {
    public static void main(String[] args) {
        Properties properties = new Properties();

        //判断翻译
        properties.put("content-type","application/x-www-form-urlencoded; charset=UTF-8");
        properties.put("cookie","BAIDUID=6A286E64B6DE5B9125B0BC317A77AFBA:FG=1; BIDUPSID=6A286E64B6DE5B9125B0BC317A77AFBA; PSTM=1603535268; BDUSS=1Rwdko3UVREVzJWcmJ-QTlaWDlLR0hDT1g5c342eXNIQTJ0bEUtME84RlZqN3RmRVFBQUFBJCQAAAAAAAAAAAEAAACyT-csaGV51Pi-rbXE1Pi-rQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFUClF9VApRfY; REALTIME_TRANS_SWITCH=1; FANYI_WORD_SWITCH=1; HISTORY_SWITCH=1; SOUND_SPD_SWITCH=1; SOUND_PREFER_SWITCH=1; BDUSS_BFESS=1Rwdko3UVREVzJWcmJ-QTlaWDlLR0hDT1g5c342eXNIQTJ0bEUtME84RlZqN3RmRVFBQUFBJCQAAAAAAAAAAAEAAACyT-csaGV51Pi-rbXE1Pi-rQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFUClF9VApRfY; MCITY=-75%3A; BDORZ=FFFB88E999055A3F8A630C64834BD6D0; H_PS_PSSID=; delPer=0; PSINO=6; BAIDUID_BFESS=6A286E64B6DE5B9125B0BC317A77AFBA:FG=1; BDRCVFR[tFA6N9pQGI3]=mk3SLVN4HKm; BA_HECTOR=0h010lah80058080qk1fr6qa80p; Hm_lvt_64ecd82404c51e03dc91cb9e8c025574=1603952568,1605167002,1605493681,1605593422; Hm_lpvt_64ecd82404c51e03dc91cb9e8c025574=1605593422; __yjsv5_shitong=1.0_7_c582f47c5a565f4db6f84b84e70985a92629_300_1605593422158_171.88.41.94_54219a26; yjs_js_security_passport=6bd53d762f02c02f678245b4f2e932de1962fecb_1605593422_js");
        properties.put("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        properties.put("x-requested-with","XMLHttpRequest");
        JSONObject formdata = new JSONObject();

        formdata.put("from","en");
        formdata.put("to","ch");
        formdata.put("query","week");
        formdata.put("transtype","realtime");
        formdata.put("simple_means_flag",3);
        formdata.put("sign",3);
        formdata.put("token","");
        formdata.put("domain","common");

        try {
            SpiderUtil.httpPost("https://fanyi.baidu.com/v2transapi?from=en&to=zh",properties,formdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSign(String r){
        boolean isMatches = Pattern.matches("/[\uD800-\uDBFF][\uDC00-\uDFFF]/g",r);
        if(!isMatches){
            int t = r.length();
            boolean s = t>30 && (r==""+r.substring(0,10)+r.substring((int) (Math.floor(t/2)-5),10)+r.substring(-10,10));
        }else{
        }

    }
  /*sign的计算方法
   var o = r.match(/[\uD800-\uDBFF][\uDC00-\uDFFF]/g);
        if (null === o) {
        var t = r.length;
        t > 30 && (r = "" + r.substr(0, 10) + r.substr(Math.floor(t / 2) - 5, 10) + r.substr(-10, 10))
    } else {
        for (var e = r.split(/[\uD800-\uDBFF][\uDC00-\uDFFF]/), C = 0, h = e.length, f = []; h > C; C++)
        "" !== e[C] && f.push.apply(f, a(e[C].split(""))),
                C !== h - 1 && f.push(o[C]);
        var g = f.length;
        g > 30 && (r = f.slice(0, 10).join("") + f.slice(Math.floor(g / 2) - 5, Math.floor(g / 2) + 5).join("") + f.slice(-10).join(""))
    }
    var u = void 0
            , l = "" + String.fromCharCode(103) + String.fromCharCode(116) + String.fromCharCode(107);
    u = null !== i ? i : (i = window[l] || "") || "";
        for (var d = u.split("."), m = Number(d[0]) || 0, s = Number(d[1]) || 0, S = [], c = 0, v = 0; v < r.length; v++) {
        var A = r.charCodeAt(v);
        128 > A ? S[c++] = A : (2048 > A ? S[c++] = A >> 6 | 192 : (55296 === (64512 & A) && v + 1 < r.length && 56320 === (64512 & r.charCodeAt(v + 1)) ? (A = 65536 + ((1023 & A) << 10) + (1023 & r.charCodeAt(++v)),
                S[c++] = A >> 18 | 240,
                S[c++] = A >> 12 & 63 | 128) : S[c++] = A >> 12 | 224,
                S[c++] = A >> 6 & 63 | 128),
        S[c++] = 63 & A | 128)
    }
        for (var p = m, F = "" + String.fromCharCode(43) + String.fromCharCode(45) + String.fromCharCode(97) + ("" + String.fromCharCode(94) + String.fromCharCode(43) + String.fromCharCode(54)), D = "" + String.fromCharCode(43) + String.fromCharCode(45) + String.fromCharCode(51) + ("" + String.fromCharCode(94) + String.fromCharCode(43) + String.fromCharCode(98)) + ("" + String.fromCharCode(43) + String.fromCharCode(45) + String.fromCharCode(102)), b = 0; b < S.length; b++)
    p += S
    [b],
    p = n(p, F);
        return p = n(p, D),
    p ^= s,
            0 > p && (p = (2147483647 & p) + 2147483648),
    p %= 1e6,
            p.toString() + "." + (p ^ m)*/
    /* function a(r) {
        if (Array.isArray(r)) {
            for (var o = 0, t = Array(r.length); o < r.length; o++)
                t[o] = r[o];
            return t
        }
        return Array.from(r)
    }*/
}


