package zaggy1024.combo.variant;

import zaggy1024.util.SimpleProperty;

import java.util.Iterator;
import java.util.List;

/**
 * Property used to store the variants of a block.
 *
 * @author Zaggy1024
 */
public class PropertyIMetadata<T extends IMetadata<T>> extends SimpleProperty<T> {
    public PropertyIMetadata(String name, List<T> values, Class<T> clazz) {
        super(name, values, clazz);
    }

    @Override
    public String getName(T value) {
        return value.getName();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other != null && getClass() == other.getClass()) {
            PropertyIMetadata<?> propIMeta = (PropertyIMetadata<?>) other;

            if (getName().equals(propIMeta.getName())) {
                Iterator<T> ourValIter = getAllowedValues().iterator();
                Iterator<?> otherValIter = propIMeta.getAllowedValues().iterator();

                while (ourValIter.hasNext() && otherValIter.hasNext()) {
                    if (ourValIter.next() != otherValIter.next()) {
                        return false;
                    }
                }

                if (!ourValIter.hasNext() && !otherValIter.hasNext()) {
                    return true;
                }
            }
        }

        return false;
    }
}
