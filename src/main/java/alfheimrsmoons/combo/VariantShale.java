package alfheimrsmoons.combo;

import zaggy1024.combo.variant.IMetadata;

public enum VariantShale implements IMetadata<VariantShale> {
    NORMAL("normal", "shale"),
    MOSSY("mossy", "mossy_shale"),
    SMOOTH("smooth", "smooth_shale"),
    BRICKS("bricks", "shale_bricks");

    private final String name;
    private final String resourceName;

    VariantShale(String name, String resourceName) {
        this.name = name;
        this.resourceName = resourceName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTranslationKey() {
        return name;
    }

    public String getResourceName() {
        return resourceName;
    }

    @Override
    public String toString() {
        return name;
    }
}
