package ch.ebynaqon.aoc.aoc24.day09;

record Block(int start, int length, int id) {
    public long checksum() {
        long result = 0;
        for (long i = 0; i < length; i++) {
            result += (start + i) * id;
        }
        return result;
    }
}
