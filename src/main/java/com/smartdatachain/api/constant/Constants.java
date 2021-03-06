package com.smartdatachain.api.constant;

import com.smartdatachain.api.integration.bo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 * @author haolenyan
 */
public class Constants {

    public static final String FILE_SEP = System.getProperty("file.separator");

    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    public static final String ACTION = ".action";

    public static final  long COOKIE_MAX_AGE = 60 * 60 * 24 * 7 * 4L; // 默认cookie保存4个星期
    public static final  int COOKIE_TWO_YEAR_AGE = 60 * 60 * 24 * 365 * 2; // 两年的cookie
    public static final  int COOKIE_ONE_YEAR_AGE = 60 * 60 * 24 * 365 * 1;
    public static final  int COOKIE_TWO_MONTH_AGE = 60 * 60 * 24 * 7 * 4;// 60天的cookie

    public static final String WEB_LOGIN_KEY = "WEIXIN_VERIFY_KEY";
    public static final String LINK_USER_COOKIE_LOGIN = "lk_sess";

    // 用于自动登录，后续采用分离式cache
    protected static final Map<String, User> SESSION_CACHE = new HashMap<>();


    /**
     * 用户注册来源常量
     */
    public static final int USER_REGISTE_FROM_WEIXIN = 1; //从微信来源注册
    public static final int USER_REGISTE_FROM_WEIBO = 2; //从微博哦来源注册
    public static final int USER_REGISTE_FROM_SNAPCHAT = 21; //从微信来源注册



    /**
     * 字段注释
     * session存放的用户对象key值
     */
    public static final String WITH_SESSION_USER = "withSessionUser";
    /**
     * 字段注释
     * cookie自动登陆保存key
     */
    public static final String LOGIN_COOKIE_SIGN = "with_cookie_sign";

    /**
     * 后台系统的高亮菜单id
     */
    public static final String BOSS_MENU_ID = "trans_boss_menu_id";

    /**
     * 字段注释
     * request 对象里的userbo数据
     */
    public static final String REQUEST_USERBO = "user";

    /**
     * 字段注释
     * 登陆验证秘钥key
     */
    public static final String LOGIN_KEY = "WITH_LOGIN_VERIFY_KEY";

    /**
     * param 的type
     */
    public static final String PARAM_TYPE_TYPEOF = "typeOf";
    public static final String PARAM_TYPE_CRAWL_WAY = "crawlWay";
    public static final String PARAM_TYPE_EXPORTSELECT = "exportSelect";
    public static final String PARAM_TYPE_FILETYPE="ExportFileType";

    /**
     * 项目导出 常量
     */
    public static final String PROJECTEXPORT_TYPE = "projectOutput";//普通项目导出
    public static final String VISPROJECTEXPORT_TYPE = "visProjectExport";//可视化项目导出
    public static final String PROJECTEXPORT_EXECUTEUrl = "/workFlow/executeWorkFlow.json";
    public static final String PROJECTEXPORT_PROGRESSURL = "/workFlow/getExecuteStatus.json";
    public static final String PROJECTEXPORT_RESULTURL = "/workFlow/getWorkFlowResultParams.json";
    private Constants() {
        throw new IllegalAccessError("Constants class");
    }

}
