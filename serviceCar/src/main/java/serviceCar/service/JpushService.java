package serviceCar.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;


@Service("jpushService")
public class JpushService {
	private final static String MasterSecret = "9b9607f09bddf86cbf41e6f4";
	private final static String APPKey = "3a738760955e1a3bfe047f66";
	public JPushClient jPushClient = null;
	private final String TAG = "Jpush";
	private Logger logger = Logger.getLogger(JpushService.class);
	
	/**
	 * 推送到所有平台,别名为alias
	 * 
	 * @param alias
	 * @param content
	 * @return
	 */
	public String push_allPlatform_Alias(String alias, String message, String content) {
		JPushClient jPushClient = new JPushClient(MasterSecret, APPKey);
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
				.setNotification(Notification.alert(message)).setMessage(Message.content(content)).build();
		try {
			PushResult result = jPushClient.sendPush(payload);
			logger.info(TAG + result);
			return null;
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
			return "Connection error, should retry later";

		} catch (APIRequestException e) {
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			return e.getErrorMessage();
		}
	}

	/**
	 * 推送到别名
	 * 
	 * @param tag
	 * @param title
	 * @param content
	 * @return
	 */
	public void buildPushObject_android_tag_alertWithTitle(String tag, String title, String content) {
		JPushClient jPushClient = new JPushClient(MasterSecret, APPKey);
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag(tag))
				.setNotification(Notification.android(content, title, null)).build();
		try {
			PushResult result = jPushClient.sendPush(payload);
			logger.info(TAG + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);

		} catch (APIRequestException e) {
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
	}
	
    public void notifyWithExtra(Integer userId, 
    		String message, String content, Map<String, String> extra) {
		String alias = userId.toString();
    	JPushClient jPushClient = new JPushClient(MasterSecret, APPKey);
		PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(message)
                                .setSound("default")
                                .addExtras(extra)
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                        		.setAlert(message)
                                .addExtras(extra)
                        		.build())
                        .build())
                 .setMessage(Message.content(content))
                 .build();
		try {
			PushResult result = jPushClient.sendPush(payload);
			logger.info(TAG + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
    } 
}
