package com.loopeer.android.librarys.umeng_social;

import android.app.Activity;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

public class UmengSocialTool {

    private static UmengSocialTool sInstance;

    private UmengSocialTool() {
        setupKey();
        config();
    }

    private void config() {
        //关闭Log
        Log.LOG = false;
        //关闭Toast
        Config.IsToastTip = false;
    }

    private void setupKey() {
        //微信 appid appsecret
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    public synchronized static UmengSocialTool getInstance() {
        if (sInstance == null)
            sInstance = new UmengSocialTool();
        return sInstance;
    }

    /**
     * URL音频与图片
     * UMusic music = new UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
     * music.setTitle("Title");
     * music.setThumb(new UMImage(ShareActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png"));
     * <p/>
     * URL视频
     * UMVideo video = new UMVideo("http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html");
     */

    public void openShare(Activity activity, SHARE_MEDIA shareMedia, ShareContent content, UMShareListener listener) {
        new ShareAction(activity)
                .setPlatform(shareMedia)
                .setCallback(listener)
                .withTitle(content.title)
                .withText(content.content)
                .withTargetUrl(content.targetUrl)
                        //本地图片资源引用：new UMImage(activity,BitmapFactory.decodeResource(getResources(), R.drawable.image))
                        //本地图片绝对路径：new UMImage(activity, BitmapFactory.decodeFile("/mnt/sdcard/icon.png")))
                .withMedia(new UMImage(activity, content.imageUrl))
                .share();
    }

    public void doLogin(Activity activity, SHARE_MEDIA shareMedia, UMAuthListener listener) {
        UMShareAPI shareAPI = UMShareAPI.get(activity);
        shareAPI.doOauthVerify(activity, shareMedia, listener);
    }

}
