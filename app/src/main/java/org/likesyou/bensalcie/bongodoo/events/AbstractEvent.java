package org.likesyou.bensalcie.bongodoo.events;

public abstract class AbstractEvent implements Event {

	protected abstract void fire(EventObserver eventObserver);

}
