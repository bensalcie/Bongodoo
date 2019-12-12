package org.likesyou.bensalcie.bongodoo.events;


import org.likesyou.bensalcie.bongodoo.events.engine.FlipDownCardsEvent;
import org.likesyou.bensalcie.bongodoo.events.engine.GameWonEvent;
import org.likesyou.bensalcie.bongodoo.events.engine.HidePairCardsEvent;
import org.likesyou.bensalcie.bongodoo.events.ui.BackGameEvent;
import org.likesyou.bensalcie.bongodoo.events.ui.DifficultySelectedEvent;
import org.likesyou.bensalcie.bongodoo.events.ui.FlipCardEvent;
import org.likesyou.bensalcie.bongodoo.events.ui.NextGameEvent;
import org.likesyou.bensalcie.bongodoo.events.ui.ResetBackgroundEvent;
import org.likesyou.bensalcie.bongodoo.events.ui.StartEvent;
import org.likesyou.bensalcie.bongodoo.events.ui.ThemeSelectedEvent;

public class EventObserverAdapter implements EventObserver {

	public void onEvent(FlipCardEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(HidePairCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(FlipDownCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(GameWonEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(BackGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(NextGameEvent event) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		throw new UnsupportedOperationException();		
	}

}
