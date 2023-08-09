package org.utilityclient.api;

import org.utilityclient.api.abstraction.StandaloneCompatible;

/**
 * Make a Type registrable with @Register.
 * @author Sam302
 * @since 3.0
 * @see Register
 */
@StandaloneCompatible
public abstract class Registrable extends Instances {
    /**
     * Register "this" to your system.
     * @since 3.0
     */
    public abstract void register();
}
