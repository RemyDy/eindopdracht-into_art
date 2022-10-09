package com.eindopdracht_into_art.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String dimensions;
    private boolean hasFrame;
    private boolean hasPassepartout;
    private String dimensionsInclFrame;
    private boolean isForSale;
    private boolean isSold;

    protected Artwork() {
    }

    private Artwork(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.price = builder.price;
        this.dimensions = builder.dimensions;
        this.hasFrame = builder.hasFrame;
        this.hasPassepartout = builder.hasPassepartout;
        this.dimensionsInclFrame = builder.dimensionsInclFrame;
        this.isForSale = builder.isForSale;
        this.isSold = builder.isSold;
    }

    //region Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDimensions() {
        return dimensions;
    }

    public boolean isHasFrame() {
        return hasFrame;
    }

    public boolean isHasPassepartout() {
        return hasPassepartout;
    }

    public String getDimensionsInclFrame() {
        return dimensionsInclFrame;
    }

    public boolean isForSale() {
        return isForSale;
    }

    public boolean isSold() {
        return isSold;
    }
    //endregion

    public static class Builder {
        private String title;
        private String description;
        private BigDecimal price;
        private String dimensions;
        private boolean hasFrame;
        private boolean hasPassepartout;
        private String dimensionsInclFrame;
        private boolean isForSale;
        private boolean isSold;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setDimensions(String dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setHasFrame(boolean hasFrame) {
            this.hasFrame = hasFrame;
            return this;
        }

        public Builder setHasPassepartout(boolean hasPassepartout) {
            this.hasPassepartout = hasPassepartout;
            return this;
        }

        public Builder setDimensionsInclFrame(String dimensionsInclFrame) {
            this.dimensionsInclFrame = dimensionsInclFrame;
            return this;
        }

        public Builder setForSale(boolean forSale) {
            isForSale = forSale;
            return this;
        }

        public Builder setSold(boolean sold) {
            isSold = sold;
            return this;
        }

        public Artwork build() {
            return new Artwork(this);

        }
    }

}



