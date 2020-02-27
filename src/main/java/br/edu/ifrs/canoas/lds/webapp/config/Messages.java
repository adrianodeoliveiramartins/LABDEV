package br.edu.ifrs.canoas.lds.webapp.config;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

/**
 * Created by rodrigo on 3/16/17.
 */
@Component
public class Messages {

	private MessageSourceAccessor accessor;
	private final MessageSource messageSource;

	public Messages(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale());
	}

	public String get(String code) {
		return accessor.getMessage(code, LocaleContextHolder.getLocale());
	}

}