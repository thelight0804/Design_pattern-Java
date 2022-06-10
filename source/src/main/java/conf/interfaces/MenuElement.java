package conf.interfaces;

import java.util.function.Supplier;

/**
 * please override this interface on each Context Enum to force enum to have fields.
 */
public interface MenuElement {
    String getName();
    String getDescription();
    Supplier<Manager> getManagerSupplier();
}
