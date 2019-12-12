package org.likesyou.bensalcie.bongodoo.events.ui;

import org.likesyou.bensalcie.bongodoo.events.AbstractEvent;
import org.likesyou.bensalcie.bongodoo.events.EventObserver;
import org.likesyou.bensalcie.bongodoo.themes.Theme;

public class ThemeSelectedEvent extends AbstractEvent {

	public static final String TYPE = ThemeSelectedEvent.class.getName();
	public final Theme theme;

	public ThemeSelectedEvent(Theme theme) {
		this.theme = theme;
	}

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
