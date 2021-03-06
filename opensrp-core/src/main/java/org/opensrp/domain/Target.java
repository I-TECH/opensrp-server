package org.opensrp.domain;

import org.joda.time.LocalDate;

/**
 * Created by samuelgithengi on 4/29/19.
 */
public class Target {

    private String measure;

    private Detail detail;

    private LocalDate due;

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    static class Detail {
        private Measure detailQuantity;

        private MeasureRange detailRange;

        private DetailCodableConcept detailCodableConcept;

        public Measure getDetailQuantity() {
            return detailQuantity;
        }

        public void setDetailQuantity(Measure detailQuantity) {
            this.detailQuantity = detailQuantity;
        }

        public MeasureRange getDetailRange() {
            return detailRange;
        }

        public void setDetailRange(MeasureRange detailRange) {
            this.detailRange = detailRange;
        }

        public DetailCodableConcept getDetailCodableConcept() {
            return detailCodableConcept;
        }

        public void setDetailCodableConcept(DetailCodableConcept detailCodableConcept) {
            this.detailCodableConcept = detailCodableConcept;
        }
    }

    static class Measure {
        private float value;

        private String comparator;

        private String unit;

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public String getComparator() {
            return comparator;
        }

        public void setComparator(String comparator) {
            this.comparator = comparator;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    static class MeasureRange {
        private Measure high;

        private Measure low;

        public Measure getHigh() {
            return high;
        }

        public void setHigh(Measure high) {
            this.high = high;
        }

        public Measure getLow() {
            return low;
        }

        public void setLow(Measure low) {
            this.low = low;
        }
    }

    static class DetailCodableConcept {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}


