package org.utilityclient.addons.events;

import org.utilityclient.addons.Event;

/**
 * @author Sam302
 * @since 3.0
 */
public abstract class AddonLoadEvent implements Event {
    public abstract void onLoad();
}
