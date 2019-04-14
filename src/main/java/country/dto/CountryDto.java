package country.dto;

import common.business.dto.BaseDto;
import country.domain.Discriminator;

public abstract class CountryDto extends BaseDto<Long> {
    protected String name;
    protected String language;
    protected int telephoneCode;
    protected Discriminator discriminator;

    CountryDto() {
        initDiscriminator();
    }

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

    public int getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(int telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    protected abstract void initDiscriminator();

    public Discriminator getDiscriminator() {
        return discriminator;
    }

}


