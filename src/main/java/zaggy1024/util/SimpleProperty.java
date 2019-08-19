package zaggy1024.util;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.properties.PropertyHelper;

import java.util.Collection;
import java.util.List;

/**
 * Simplification of {@link PropertyHelper} to make a generic parseValue function that pulls the
 * names from getName(value).
 *
 * @author Zaggy1024
 */
public abstract class SimpleProperty<T extends Comparable<T>> extends PropertyHelper<T> {
    private final ImmutableList<T> values;
    private final ImmutableMap<String, T> parseMap;

    protected SimpleProperty(String name, List<T> values, Class<T> valueClass) {
        super(name, valueClass);

        this.values = ImmutableList.copyOf(values);
        parseMap = values.stream().collect(StreamUtils.toImmMap((v) -> getName(v), (v) -> v));
    }

    @Override
    public final Collection<T> getAllowedValues() {
        return values;
    }

    @Override
    public final Optional<T> parseValue(String value) {
        if (parseMap.containsKey(value))
            return Optional.of(parseMap.get(value));

        return Optional.absent();
    }
}
