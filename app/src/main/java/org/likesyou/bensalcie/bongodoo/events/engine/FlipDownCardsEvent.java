package org.likesyou.bensalcie.bongodoo.events.engine;

import org.likesyou.bensalcie.bongodoo.events.AbstractEvent;
import org.likesyou.bensalcie.bongodoo.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class FlipDownCardsEvent extends AbstractEvent {

	public static final String TYPE = FlipDownCardsEvent.class.getName();

	public FlipDownCardsEvent() {
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
