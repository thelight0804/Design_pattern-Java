package conf.interfaces;

import java.util.function.Supplier;

/**
 * please override this interface on each Context Enum to force enum to have fields.
 */
public interface MenuElement extends Element {
    Supplier<Manager> getManagerSupplier();
}
