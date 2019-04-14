package common.business.search;

public class Paginator {
    private int limit = 2;
    private int offset = 0;

    Paginator() {
    }

    public Paginator(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

}
