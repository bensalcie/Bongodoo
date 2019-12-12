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

public interface EventObserver {

	void onEvent(FlipCardEvent event);

	void onEvent(DifficultySelectedEvent event);

	void onEvent(HidePairCardsEvent event);

	void onEvent(FlipDownCardsEvent event);

	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);

	void onEvent(GameWonEvent event);

	void onEvent(BackGameEvent event);

	void onEvent(NextGameEvent event);

	void onEvent(ResetBackgroundEvent event);

}
