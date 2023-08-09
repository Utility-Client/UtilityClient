package org.utilityclient.utils.callback;

import org.utilityclient.api.abstraction.StandaloneCompatible;

@StandaloneCompatible
public interface GenericCallback<V> {
    void callback(V v);
}
