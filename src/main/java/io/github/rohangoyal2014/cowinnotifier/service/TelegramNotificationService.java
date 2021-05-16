package io.github.rohangoyal2014.cowinnotifier.service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.rohangoyal2014.cowinnotifier.model.AvailableCentre;
import io.github.rohangoyal2014.cowinnotifier.utils.HttpUtils;
import io.github.rohangoyal2014.cowinnotifier.utils.MessagingUtils;

@Service("telegram-service")
public class TelegramNotificationService implements INotificationService {

	public static final Logger LOGGER = LoggerFactory.getLogger(TelegramNotificationService.class);

	private final String botApiKey;
	private final String chatId;
	private final String url;
	private final String urlParamChatId;
	private final String urlParamText;
	
	private final AlertService alertService;

	public TelegramNotificationService(@Value("${TELEGRAM_BOT_KEY}") String botApiKey,
			@Value("${CHAT_ID}") String chatId, @Value("${telegram.url}") String url,
			@Value("${telegram.url.param.chat_id}") String urlParamChatId,
			@Value("${telegram.url.param.text}") String urlParamText,
			AlertService alertService) {
		this.botApiKey = botApiKey;
		this.chatId = chatId;
		this.url = MessageFormat.format(url, this.botApiKey);
		this.urlParamChatId = urlParamChatId;
		this.urlParamText = urlParamText;
		
		this.alertService = alertService;
	}

	@Override
	public void send(List<AvailableCentre> centres) {
		if (centres.isEmpty()) {
			LOGGER.info("No availables centres found on provided districts!");
			return;
		}
		LOGGER.info("Found available centres:" + centres);
		
		String message = MessagingUtils.buildMessageBody(centres);
		
		if(message == null) {
			LOGGER.info("Current Message must be same as previously delivered message, hence skipping...");
			return;
		}

		Map<String, String> headers = new HashMap<>();
		headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36");
		headers.put("accept-language", "en-US,en;q=0.9");

		Map<String, String> params = new HashMap<>();
		params.put(urlParamChatId, chatId);
		params.put(urlParamText, message);
		

		try {
			Pair<Integer, String> response = HttpUtils.performGet(url, params, headers);
			if(response.getLeft() == 200) {
				LOGGER.info("Message Delivered!");
			}
			else {
				throw new Exception("Response Code is:" + response.getLeft() + ", response body:" + response.getRight());
			}
		} catch (Exception e) {
			LOGGER.error("Error:" + e, e);
			alertService.alert("Error:" + e);
		}

	}

}
