package org.likesyou.bensalcie.bongodoo.events.engine;


import org.likesyou.bensalcie.bongodoo.events.AbstractEvent;
import org.likesyou.bensalcie.bongodoo.events.EventObserver;
import org.likesyou.bensalcie.bongodoo.model.GameState;

/**
 * When the 'back to menu' was pressed.
 */
public class GameWonEvent extends AbstractEvent {

	public static final String TYPE = GameWonEvent.class.getName();

	public GameState gameState;

	
	public GameWonEvent(GameState gameState) {
		this.gameState = gameState;
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
