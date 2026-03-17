public class BitMaskFilter implements Filter {
    private long[] masks;
    private int count;

    public BitMaskFilter() {
        masks = new long[1];
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isPresent(int index) {
        if (index >= masks.length * 64)
            return false;
        int mask = index / 64;
        int pos = index % 64;
        return (masks[mask] & (1L << pos)) != 0;
    }

    @Override
    public void add(int index) {
        if (index >= masks.length * 64) {
            int newSize = Math.max(index / 64 + 1, masks.length * 2);
            long[] newMasks = new long[newSize];
            System.arraycopy(masks, 0, newMasks, 0, masks.length);
            masks = newMasks;
        }
        if (!isPresent(index)) {
            int mask = index / 64;
            int pos = index % 64;
            masks[mask] |= 1L << pos;
            count++;
        }
    }

    @Override
    public void remove(int index) {
        if (index >= masks.length * 64)
            return;
        if (isPresent(index)) {
            int mask = index / 64;
            int pos = index % 64;
            masks[mask] &= ~(1L << pos);
            count--;
        }
    }
}