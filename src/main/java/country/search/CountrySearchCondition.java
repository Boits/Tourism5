package country.search;

import common.business.search.BaseSearchCondition;
import country.domain.Discriminator;

public class CountrySearchCondition extends BaseSearchCondition<Long> {
    private String name;
    private String language;
    private Discriminator discriminator;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Discriminator getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(Discriminator discriminator) {
        this.discriminator = discriminator;
    }
}